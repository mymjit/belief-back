package com.whilte.controller;

import com.whilte.domail.User;
import com.whilte.manager.impl.RedisTokenManager;
import com.whilte.model.ResultModel;
import com.whilte.model.TokenModel;
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

    @Autowired
    private RedisTokenManager redisTokenManager;
    /**
      * @Method       : login
      * @MethodParam  : [user]
      * @MethodReturn : com.whilte.model.ResultModel
      * @Description  : 登入接口
      */
    @PostMapping("/login")
    public ResultModel login(User user,HttpSession session){
        logger.info("UserController -> login ：sessionId : {}",session.getId());
        return ResultUtil.success();
    }


    /**
      * @Method       : register
      * @MethodParam  : [user]
      * @MethodReturn : com.whilte.model.ResultModel
      * @Description  : 注册接口
      */
    @PostMapping("/register")
    public ResultModel register (User user, HttpSession session){
        resultModel = ResultUtil.error();
        if ( null != user ){
            if ( !"".equals(user.getTelephoneNumber()) &&  10 < user.getTelephoneNumber().length() ){
                if ( !"".equals(user.getPassword()) && 7 < user.getPassword().length() ){
                    resultModel = userService.userRegister(user);
                    session.setAttribute("user",resultModel.getData());
                    String id = session.getId();
                    TokenModel tokenModel = redisTokenManager.createToken(id);
                    tokenModel.setId(null);
                    resultModel.setData(tokenModel );
                } else {
                    resultModel.setMsg("请输入正确的密码信息！");
                }
            } else {
                resultModel.setMsg("请输入正确的账号信息！");
            }
        } else {
            resultModel.setMsg("请填写账号密码！");
        }
        return resultModel;
    }

}
