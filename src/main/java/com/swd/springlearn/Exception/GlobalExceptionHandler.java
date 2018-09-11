package com.swd.springlearn.Exception;

import com.swd.springlearn.entity.ErrorResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author swd
 * @ClassName: GlobalExceptionHandler
 * @ProjectName springlearn
 * @Description: 异常全局处理
 * @date 2018/9/1014:16
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * 定义要捕获的异常，可以多个@Exception({})
     * @param request
     * @param e
     * @param response
     * @return 自定义异常实体
     */
    @ExceptionHandler(CustomException.class)
    public ErrorResponseEntity customExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        CustomException exception = (CustomException) e;
        return new ErrorResponseEntity(exception.getCode(),exception.getMessage());
    }

    /**
     * 捕获ruantimeException
     * 当然你也可以使用 if (e instanceof xxxException)在一个方法中处理
     * @param request
     * @param e
     * @param response
     * @return 自定义异常实体
     */
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseEntity runExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        RuntimeException exception = (RuntimeException) e;
        return new ErrorResponseEntity(400,exception.getMessage());
    }

    /**
     *通用的接口映射异常处理方法
     *  @param ex
     * @param body
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request){
        if(ex instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            return new ResponseEntity<>(new ErrorResponseEntity(status.value(),exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()),status);
        }
        if (ex instanceof MethodArgumentTypeMismatchException){
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
            logger.error("参数转换失败，方法："+exception.getParameter().getMethod().getName()+"---参数："+exception.getName()+"---信息："+exception.getLocalizedMessage());
            return new ResponseEntity<>(new ErrorResponseEntity(status.value(),"参数转换失败"),status);
        }
        return new ResponseEntity<>(new ErrorResponseEntity(status.value(),"未知错误"),status);
    }
}
