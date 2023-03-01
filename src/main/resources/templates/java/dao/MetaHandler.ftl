package ${basePackage}.${appProjectNameToCamelCase}.dao.meta.handler;

import cn.zy.common.request.utils.CurrentReqUtils;
import cn.zy.common.user.User;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MetaHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("addTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        setUserParam(metaObject, true);
    }

    private void setUserParam(MetaObject metaObject, boolean insert) {
        User user = CurrentReqUtils.getUser();
        if (user == null) {
            return;
        }
        if (insert) {
            this.setFieldValByName("addUser", user.getName(), metaObject);
            this.setFieldValByName("addUserId", user.getId(), metaObject);
        }
        this.setFieldValByName("updateUser", user.getName(), metaObject);
        this.setFieldValByName("updateUserId", user.getId(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        setUserParam(metaObject, false);
    }

}