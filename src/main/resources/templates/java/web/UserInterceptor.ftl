package ${basePackage}.${appProjectNameToCamelCase}.web.config.springmvc;

import cn.hutool.core.util.StrUtil;
import cn.zy.common.enums.TerminalType;
import cn.zy.common.req.ReqData;
import cn.zy.common.request.utils.CurrentReqUtils;
import cn.zy.common.user.User;
import cn.zy.common.dto.UserInfo;
import ${basePackage}.${appProjectNameToCamelCase}.web.config.common.ReqRespConstant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.internal.Util;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * spring拦截器, 用于写入用户信息到 ThreadLocal
 */
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    private String userInfoUrl;

    public UserInterceptor(String url) {
        this.userInfoUrl = url;
    }

    private static OkHttpClient OKHTTP_CLIENT;

    static {
        OKHTTP_CLIENT = new OkHttpClient().newBuilder()
                .dispatcher(new Dispatcher(new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(),
                        Util.threadFactory("UserInterceptor", false))))
                .connectionPool(new ConnectionPool(10, 5L, TimeUnit.MINUTES))
                .connectTimeout(5L, TimeUnit.SECONDS)
                .readTimeout(10L, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userStr = request.getHeader(ReqRespConstant.USER_HEADER);
        if (StrUtil.isNotEmpty(userStr)) {
            log.info("请求头里面已经包含了用户信息, userInfo:{}", userStr);
            fillUserInfoToThreadLocal(userStr);
            return true;
        }
        String authorization = request.getHeader("Authorization");
        if (StrUtil.isEmpty(authorization)) {
            log.warn("not found Authorization in requestHeader");
            fillUserInfoToThreadLocal(new User());
            return true;
        }
        log.info("请求头里面未包含用户信息, 调用接口获取, Authorization:{}", authorization);
        Request okHttpRequest = new Request.Builder()
                .url(userInfoUrl)
                .header("Authorization", authorization)
                .build();
        try {
            Response okHttpResp = OKHTTP_CLIENT
                    .newCall(okHttpRequest)
                    .execute();
            if (okHttpResp.isSuccessful() && !Objects.isNull(okHttpResp.body())) {
                String result = new String(okHttpResp.body().bytes());
                if (log.isDebugEnabled()) {
                    log.debug("返回的响应为: result: {}", result);
                }
                JSONObject jsonObject = JSON.parseObject(result);
                UserInfo userInfo = jsonObject.getObject("data", UserInfo.class);
                // 请求体内用户信息转成通用的用户实例
                User user = convertToUser(userInfo);
                log.info("查询用户信息成功, userInfo:{}", JSON.toJSONString(userInfo));
                fillUserInfoToThreadLocal(user);
            } else {
                log.warn("获取用户信息失败, 写入默认用户");
                fillUserInfoToThreadLocal(new User());
            }
            return true;
        } catch (Exception e) {
            // 兜底
            log.warn("请求失败, 写入默认用户, e", e);
            fillUserInfoToThreadLocal(new User());
        }
        return true;
    }

    private User convertToUser(UserInfo userInfo) {
        User user = new User();
        user.setId(userInfo.getUserId());
        user.setStaffId(userInfo.getUserId());
        user.setStaffName(userInfo.getUserName());
        user.setName(userInfo.getUserName());
        user.setAdmin(userInfo.isAdministrator());
        user.setLoginName(userInfo.getUserAccount());
        user.setDepartmentId(userInfo.getDepartmentId());
        user.setStatus(true);
        String loginDevice = userInfo.getLoginDevice();
        switch (loginDevice) {
            case "h5":
            case "H5":
                user.setTerminalType(TerminalType.H5);
                break;
            case "pc":
            case "PC":
                user.setTerminalType(TerminalType.PC);
                break;
            case "wx":
            case "WX":
            case "WEIXIN":
            case "weixin":
                user.setTerminalType(TerminalType.WEIXIN);
            default:
                break;
        }
        return user;
    }

    /**
     * 填充用户信息到 ThreadLocal
     *
     * @param result
     */
    private void fillUserInfoToThreadLocal(String result) {
        User user = JSON.parseObject(result, User.class);
        fillUserInfoToThreadLocal(user);
    }

    private void fillUserInfoToThreadLocal(User user) {
        ReqData reqData = new ReqData(user);
        CurrentReqUtils.setReqData(reqData);
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        CurrentReqUtils.remove();
    }
}
