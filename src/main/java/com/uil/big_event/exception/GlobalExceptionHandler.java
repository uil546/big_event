package com.uil.big_event.exception;

import com.uil.big_event.pojo.Result;
import jakarta.validation.ConstraintViolationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)   //处理各种错误的异常处理器
    public Result exceptionHandle(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage():"出现异常，操作失败！");
    }

    /*@ExceptionHandler(ConstraintViolationException.class)
    public Result exceptionHandle(ConstraintViolationException e) {
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "出现异常，操作失败！");
    }*/
}
