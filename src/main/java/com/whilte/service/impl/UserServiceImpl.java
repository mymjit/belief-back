package com.whilte.service.impl;

import com.whilte.domail.User;
import com.whilte.model.ResultModel;
import com.whilte.repository.UserRepository;
import com.whilte.service.UserService;
import com.whilte.service.redis.RedisService;
import com.whilte.util.Md5Util;
import com.whilte.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private ResultModel resultModel;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserRepository userRepository;

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
      * @Method       : userLogin
      * @MethodParam  : [user]
      * @MethodReturn : com.whilte.model.ResultModel
      * @Description  : 用户注册流程
      * 1. 判断数据是否存在相同的账号信息
      * 2. 没有相同的账号做注册 ，有返回错误信息
      * 3. 注册成功，直接登入
      */
    @Override
    public ResultModel userRegister(User user) {
        resultModel = ResultUtil.error();
        List<User> users = userRepository.getByTelephoneNumber(user.getTelephoneNumber());
        if ( 0 < users.size() ){
            resultModel.setMsg("账号已存在！");
        } else {
            // 设置初始数据初始状态
            user.setState(1);
            // 设置初始积分
            user.setIntegral(0);
            // 对密码进行加密
            user.setPassword(Md5Util.getSecret(user.getPassword()));
            user = userRepository.save( user );
            redisService.set(user);
            resultModel = ResultUtil.success();
            resultModel.setMsg("注册成功！");
            user.setPassword(null);
            resultModel.setData(user);
        }
        return resultModel;
    }

    /**
     * @Method       : login
     * @MethodParam  : [user]
     * @MethodReturn : com.whilte.model.ResultModel
     * @Description  : 用户登入处理
     */
    @Override
    public ResultModel userLogin(User user) {
        resultModel = ResultUtil.error();
        User baseUser = userRepository.getUserByTelephoneNumber(user.getTelephoneNumber());
        if ( null != baseUser ){
            if (Md5Util.checkPassword( user.getPassword(), baseUser.getPassword() )){
                redisService.set(user);
                resultModel = ResultUtil.success();
                // 将密码清空
                user.setPassword(null);
                resultModel.setData(user);
                resultModel.setMsg("登入成功！");
            } else {
                resultModel.setMsg("密码错误！");
            }
        } else {
            resultModel.setMsg("账号不存在！");
        }
        return resultModel;
    }

    /**
      * @Method       : signOut
      * @MethodParam  : [user]
      * @MethodReturn : void
      * @Description  : 退出
      */
    @Override
    public void signOut(User user) {
        redisService.delete(user);
    }
}
