package com.whilte.controller;

import com.whilte.domail.User;
import com.whilte.model.ResultModel;
import com.whilte.service.UserService;
import com.whilte.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    @Autowired
    private ResultModel resultModel;

    @Autowired
    private UserService userService;


    /**
      * @Method       : login
      * @MethodParam  : [user]
      * @MethodReturn : com.whilte.model.ResultModel
      * @Description  : 登入接口
      */
    @PostMapping("/login")
    public ResultModel login(User user){
        resultModel = loginAndRegisterUserVerification(user);
        if ( 200 == resultModel.getCode() ){
            resultModel = userService.userLogin(user);
        }
        return resultModel;
    }


    /**
      * @Method       : register
      * @MethodParam  : [user]
      * @MethodReturn : com.whilte.model.ResultModel
      * @Description  : 注册接口
      */
    @PostMapping("/register")
    public ResultModel register (User user){
        resultModel = loginAndRegisterUserVerification(user);
        if ( 200 == resultModel.getCode() ){
            resultModel = userService.userRegister(user);
        }
        return resultModel;
    }

    @PostMapping("/signOut")
    public void signOut( User user ){
        if ( null != user ){
            if ( null != user && !"".equals( user.getTelephoneNumber() ) ){
                userService.signOut(user);
            }
        }
    }



    /**
      * @Method       : 
      * @MethodParam  : 
      * @MethodReturn :  
      * @Description  : 注册登入对账号和密码的校验
      */
    private ResultModel loginAndRegisterUserVerification(User user){
        resultModel = ResultUtil.error();
        // 手机号码正则表达式
        String telephoneNumberRegex ="^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        // 密码正则表达式
        String passwordRegex  = "(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        if ( null != user ) {
            if ( user.getTelephoneNumber().matches( telephoneNumberRegex ) ){
                if ( user.getPassword().matches( passwordRegex ) ){
                    resultModel.setCode(200);
                    resultModel.setMsg("账号密码验证成功！");
                } else {
                    resultModel.setMsg("请填写正确的密码信息!");
                }
            } else {
                resultModel.setMsg("请填写正确的账号信息!");
            }
        } else {
            resultModel.setMsg("请填写账号密码！");
        }
        return  resultModel;
    }

}
