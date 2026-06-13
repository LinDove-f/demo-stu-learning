package com.attust.mp.handler;

import com.attust.mp.common.Result;
import com.attust.mp.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author fqlStart
 * {@code @create} 2026-06-13-18:57
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Result<Void>> handlerBusinessException(BusinessException e){
        return ResponseEntity
                .status(e.getCode())
                .body(Result.error(e.getCode(),e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<Void>> handlerException(Exception e){
        return ResponseEntity
                .status(500)
                .body(Result.error(500,"系统异常，请联系管理员"));
    }
}
