package com.whilte.util;

import com.whilte.enums.ResultEnum;
import com.whilte.model.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResultUtil {
    @Autowired
    private static ResultEnum resultEnum;

    public static ResultModel success(String msg , Integer code , Object obj) {
        ResultModel<Object> resultModel = new ResultModel();
        resultModel.setMsg(msg);
        resultModel.setData( obj );
        resultModel.setCode(code);
        return resultModel;
    }


    public static ResultModel success(ResultEnum resultEnum , Object obj) {
        ResultModel resultModel = new ResultModel();
        resultModel.setMsg(resultEnum.getMsg());
        resultModel.setCode(resultEnum.getCode());
        resultModel.setData(obj);
        return resultModel;
    }

    public static ResultModel success(Object object) {
        ResultModel result = new ResultModel();

        resultEnum = ResultEnum.SUCCESS;
        result.setMsg(resultEnum.getMsg());
        result.setCode(resultEnum.getCode());
        result.setData(object);
        return result;
    }


    public static ResultModel success() {
        return success(null);
    }


    public static ResultModel error(String msg , Integer code , Object object) {
        ResultModel result = new ResultModel();
        result.setMsg  ( msg );
        result.setCode ( code );
        result.setData ( object );
        return result;
    }


    public static ResultModel error(ResultEnum resultEnum , Object obj) {
        ResultModel<Object> result = new ResultModel();
        result.setMsg  ( resultEnum.getMsg() );
        result.setCode ( resultEnum.getCode() );
        result.setData ( obj );
        return result;
    }

    public static ResultModel error(Object object) {
        ResultModel result = new ResultModel();
        resultEnum = ResultEnum.ERROR;
        result.setMsg  ( resultEnum.getMsg() );
        result.setCode ( resultEnum.getCode() );
        result.setData ( object );
        return result;
    }

    public static ResultModel error(){
        return error(null );
    }

}
