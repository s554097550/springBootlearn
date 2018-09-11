package com.swd.springlearn.entity;

/**
 * @author swd
 * @ClassName: ErrorResponseEntity
 * @ProjectName springlearn
 * @Description: 异常对象
 * @date 2018/9/1014:08
 */
public class ErrorResponseEntity {
    private int code;
    private String message;

    public ErrorResponseEntity() {
    }

    public ErrorResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
