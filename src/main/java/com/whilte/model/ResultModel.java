package com.whilte.model;

import org.springframework.stereotype.Component;

/**
  * @date        : 2017/12/28
  * @author      : whilte
  * @Description : 统一的数据返回格式模型
  */
@Component
public class ResultModel<T> {

    private Integer code;
    private String  msg ;
    private T       data;

    public ResultModel() {
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
