package com.swd.springlearn.exception;

/**
 * @author swd
 * @ClassName: CustomException
 * @ProjectName springlearn
 * @Description: TODO
 * @date 2018/9/1014:02
 */
public class CustomException extends RuntimeException {
    private int code;

    public CustomException() {
        super();
    }

    public CustomException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
