package com.whilte.model;


/**
  * @date        : 2017/12/31
  * @author      : whilte
  * @Description : 用了Spring Session id是存储是session ID
  *  Token的Model类，可以增加字段提高安全性，例如时间戳、url签名
  */
public class TokenModel {


    private String id;

    private String token;

    public TokenModel() {
    }

    public TokenModel(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
