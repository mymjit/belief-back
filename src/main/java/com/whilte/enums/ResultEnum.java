package com.whilte.enums;

public enum ResultEnum {
    // 成功的
    SUCCESS                    (200,"请求成功！"),
    // 失败的
    ERROR                      (400,"请求失败！");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
