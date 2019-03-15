package com.wcx.springboot.demo.midware.json.gson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * 将Enum转换成json的字符串形式，默认是"FAIL"
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ApiResponse {
    @SerializedName("success")
    SUCC(0, "success"){
        @Override
        public String toString() {
            return "succ";
        }

    }, FAIL(1, "error");
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

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("aa", ApiResponse.SUCC);
        System.out.println(SUCC.toString());
        System.out.println(new Gson().toJson(map));
        System.out.println(new Gson().toJson(ApiResponse.FAIL.toString()));
    }

    @Override
    public String toString() {
        return getMessage();
    }}
