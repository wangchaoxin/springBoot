package com.wcx.springboot.demo.java.gson;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 将Enum转换成json的字符串形式，默认是"FAIL"
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ApiResponse {
    SUCC(0, "success"), FAIL(1, "error");
    private int code;
    private String message;

    ApiResponse(int code, String message) {
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

    public ApiResponse setMessage(String message) {
        this.message = message;
        return this;
    }

}
