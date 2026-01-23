package com.xbleey.exception;

public class GlobalException extends RuntimeException {
    private final String code;

    public GlobalException(String message) {
        this("BUSINESS_ERROR", message);
    }

    public GlobalException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
