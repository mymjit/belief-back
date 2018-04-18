package com.whilte.service.redis;

import com.whilte.domail.User;

public interface RedisService {


    void  set(User user);

    User  get(User user);

    void delete(User user);

}
