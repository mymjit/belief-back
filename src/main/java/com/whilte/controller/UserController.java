package com.whilte.controller;

import com.whilte.domail.User;
import com.whilte.model.ResultModel;
import com.whilte.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


/**
  * @date        : 2017/12/28
  * @author      : whilte
  * @Description : 操作用户的控制器(对数据进行验证)
  */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);


    /**
      * @Method       : login
      * @MethodParam  : [user]
      * @MethodReturn : com.whilte.model.ResultModel
      * @Description  : 登入接口
      */
    @PostMapping("/login")
    public ResultModel login(User user){
        logger.info("UserController -> login ：{}",user.toString());
        return ResultUtil.success();
    }


    /**
      * @Method       : register
      * @MethodParam  : [user]
      * @MethodReturn : com.whilte.model.ResultModel
      * @Description  : 注册接口
      */
    @PostMapping("/register")
    public ResultModel register (User user){
        logger.info("UserController -> register ：{}",user.toString());
        return ResultUtil.success();
    }

}
