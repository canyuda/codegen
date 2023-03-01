package ${basePackage}.${appProjectNameToCamelCase}.web.config.openfeign;

import cn.zy.common.request.utils.CurrentReqUtils;
import ${basePackage}.${appProjectNameToCamelCase}.web.config.common.ReqRespConstant;
import com.alibaba.fastjson.JSON;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * feign拦截器, 用于透传请求头信息
 */
public class OpenfeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        Map<String, String> headers = getHeaders();
        fillUserInfo(headers);
        headers.forEach(template::header);
    }

    private void fillUserInfo(Map<String, String> headers) {
        if (!headers.containsKey(ReqRespConstant.USER_HEADER)) {
            headers.put(ReqRespConstant.USER_HEADER, JSON.toJSONString(CurrentReqUtils.getUser()));
        }
    }

    /**
     * 获取 request 中的所有的 header 值
     *
     * @return
     */
    private Map<String, String> getHeaders() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
