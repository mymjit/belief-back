package com.whilte.exception;

import com.whilte.enums.ResultEnum;

public class CustomException extends RuntimeException {
    private Integer code;

    public CustomException() {
    }

    public CustomException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "UserException{" +
                "code=" + code +
                '}';
    }
}
