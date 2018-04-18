package com.whilte.service;

import com.whilte.domail.User;
import com.whilte.model.ResultModel;

public interface UserService {

    ResultModel userRegister(User user);

    ResultModel userLogin (User user);

    void signOut(User user);
}
