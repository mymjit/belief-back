package com.whilte.exception.handle;

import com.whilte.enums.ResultEnum;
import com.whilte.exception.CustomException;
import com.whilte.model.ResultModel;
import com.whilte.util.ResultUtil;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
  * @date        : 2017/12/28
  * @author      : whilte
  * @Description : 对所有异常进行拦截，针对自定义异常做处理
  */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultModel handle(Exception e) {
        logger.info("ExceptionHandle - handle : {异常拦截}");
        if (e instanceof UserException) {
            //针对自己定义的异常做处理
            CustomException exception = (CustomException) e;
            return ResultUtil.error(exception.getMessage(),exception.getCode(),null);
        } else {
            logger.error("[系统异常]{}", e);
            return ResultUtil.error();
        }
    }
}
