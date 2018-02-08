package com.json.myrpc.serializer;

/**
 * @author jasonLu
 * @date 08/02/2018 0008 11:26

 * @Description:
 */
public interface ISerializer {
    /**
     * 序列化
     *
     * @param obj
     * @param <T>
     * @return
     */
    <T> byte[] serialize(T obj);


    /**
     * 反序列化
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] data, Class<T> clazz);
}
