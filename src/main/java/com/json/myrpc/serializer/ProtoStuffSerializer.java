package com.json.myrpc.serializer;

/**
 * @author jasonLu
 * @date 08/02/2018 0008 11:07
 * @Description:
 */
public class ProtoStuffSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return null;
    }
}
