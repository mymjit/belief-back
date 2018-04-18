package com.whilte.configure;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

/**
  * @date        : 2018/1/5
  * @author      : whilte
  * @Description : 实现对象的序列化接口
  */
public class RedisObjectSerializer implements RedisSerializer<Object> {

    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    static final byte[] EMPTY_ARRAY = new byte[0];

    @Nullable
    @Override
    public byte[] serialize(@Nullable Object object) throws SerializationException {
        if (object == null) {
            return EMPTY_ARRAY;
        }

        try {
            return serializer.convert( object );
        } catch ( Exception e ) {
            return EMPTY_ARRAY;
        }
    }

    @Nullable
    @Override
    public Object deserialize(@Nullable byte[] bytes) {
        if (isEmpty(bytes)) {
            return null;
        }

        try {
            return deserializer.convert(bytes);
        } catch (Exception e) {
            throw new SerializationException("Cannot deserialize", e);
        }
    }


    private boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }

}

