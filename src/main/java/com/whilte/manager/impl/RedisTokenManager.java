package com.whilte.manager.impl;

import com.whilte.manager.TokenManager;
import com.whilte.model.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class RedisTokenManager implements TokenManager {

    private RedisTemplate redisTemplate;

    @Autowired
    public void setRedis(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        //泛型设置成Long后必须更改对应的序列化方案
        redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    @Override
    public TokenModel createToken(String id) {
        // 使用UUID作为Token源
        String token = UUID.randomUUID().toString().replace("-","");
        TokenModel model = new TokenModel(id,token);
        return model;
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = redisTemplate.boundValueOps(model.getId()).get().toString();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redisTemplate.boundValueOps(model.getId()).expire(30*60, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public TokenModel getToken(String authentication) {
        if ( null == authentication || 0 == authentication.length() ){
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用ID和源token简单拼接成的token，可以增加加密措施
        String id = String.valueOf(param[0]);
        String token = param[1];
        return new TokenModel(id, token);
    }

    @Override
    public void deleteToken(String id) {
        redisTemplate.delete(id);
    }

}
