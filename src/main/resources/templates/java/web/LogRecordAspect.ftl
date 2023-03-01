package ${basePackage}.${appProjectNameToCamelCase}.web.config.springmvc;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(-1)
@Slf4j
public class LogRecordAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    /**
     * 定义切点
     */
    @Pointcut(value = "execution(* ${basePackage}..controller..*.*(..))")
    public void excludeService() {
    }

    @Around(value = "excludeService()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Object proceed = null;
        try {
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                proceed = joinPoint.proceed();
                Long time = (System.currentTimeMillis() - startTime.get());
                log.info("请求URL:{}; Http请求方式:{}; CLASS_METHOD:{}; 请求参数:{}; 接口耗时:{}ms",
                        request.getRequestURI(),
                        request.getMethod(),
                        getHappenedPath(signature.getDeclaringTypeName(), signature.getName()),
                        JSON.toJSONString(joinPoint.getArgs()),
                        time);
            } else {
                proceed = joinPoint.proceed();
                log.warn("RequestContextHolder 中无法获取到 ServletRequestAttributes");
            }
        } catch (Exception e) {
            log.error("AOP异常接口,ex:", e);
            throw e;
        } finally {
            startTime.remove();
        }
        return proceed;
    }

    private static String getHappenedPath(String signature, String signature1) {
        return signature + "." + signature1;
    }

}
