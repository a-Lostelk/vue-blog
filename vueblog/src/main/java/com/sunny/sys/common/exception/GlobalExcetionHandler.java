package com.sunny.sys.common.exception;

import com.sunny.sys.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: Sunny
 * @Date: 2020/8/9
 * @Description: 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExcetionHandler {

    /**
     * 处理Shiro相关异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public String handle(ShiroException e) {
        log.error("授权认证异常---------{}");
        return ResultUtil.error("401", e.getMessage());
    }

    /**
     * 处理运行时异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public String handle(RuntimeException e) {
        log.error("运行时异常---------{}");
        System.out.println(e.getMessage());
        return ResultUtil.error("402", e.getMessage());
    }

    /**
     * 处理参数异常
     *ConcurrentHashMap
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String handle(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        String message = result.getAllErrors().stream().findFirst().get().getDefaultMessage();
        log.error("运行时异常---------{}", message);
        return ResultUtil.error("403", message);
    }

    /**
     * 处理运行时异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handle(IllegalArgumentException e) {
        log.error("Assert时异常---------{}");
        System.out.println(e.getMessage());
        return ResultUtil.error("405", e.getMessage());
    }

}
