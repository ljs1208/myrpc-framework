package com.json.myrpc.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author jasonLu
 * @date 08/02/2018 0008 11:06
 * @Description:
 */
public class JsonSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {

        return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return (T) JSON.parseObject(new String(data), clazz);
    }
}
