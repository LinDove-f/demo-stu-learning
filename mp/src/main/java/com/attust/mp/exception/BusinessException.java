package com.attust.mp.exception;

/**
 * @author fqlStart
 * {@code @create} 2026-06-13-18:25
 */
public class BusinessException extends RuntimeException{
    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }
}
