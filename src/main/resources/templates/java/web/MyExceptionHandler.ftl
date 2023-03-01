package ${basePackage}.${appProjectNameToCamelCase}.web.config.springmvc;

import cn.zy.common.dto.CommonResult;
import cn.zy.common.exception.BizTipException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(value = BizTipException.class)
    @ResponseBody
    public CommonResult<Void> exceptionHandler(BizTipException e) {
        log.info("发生了自定义异常, ex:", e);
        CommonResult<Void> result = new CommonResult<>();
        result.setCode(403);
        result.setMsg(e.getMessage());
        return result;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult<Void> exceptionHandler(Exception e) {
        log.info("发生了异常, ex:", e);
        CommonResult<Void> result = new CommonResult<>();
        result.setCode(500);
        result.setMsg(e.getMessage());
        return result;
    }
}
