package com.json.myrpc.engine;

import com.json.myrpc.enumer.SerializeType;
import com.json.myrpc.serializer.AvroSerializer;
import com.json.myrpc.serializer.DefaultJavaSerializer;
import com.json.myrpc.serializer.HessianSerializer;
import com.json.myrpc.serializer.ISerializer;
import com.json.myrpc.serializer.JsonSerializer;
import com.json.myrpc.serializer.MarshallingSerializer;
import com.json.myrpc.serializer.ProtoBufSerializer;
import com.json.myrpc.serializer.ProtoStuffSerializer;
import com.json.myrpc.serializer.ThriftSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jasonLu
 * @date 08/02/2018 0008 11:08
 * @Description:
 */
public class SerializerEngine {

    private static final Map<SerializeType, ISerializer> SERIALIZER_MAP = new HashMap<SerializeType, ISerializer>();

    static
    {
        SERIALIZER_MAP.put(SerializeType.AvroSerializer, new AvroSerializer());
        SERIALIZER_MAP.put(SerializeType.DefaultJavaSerializer, new DefaultJavaSerializer());
        SERIALIZER_MAP.put(SerializeType.HessianSerializer, new HessianSerializer());
        SERIALIZER_MAP.put(SerializeType.JsonSerializer, new JsonSerializer());
        SERIALIZER_MAP.put(SerializeType.MarshallingSerializer, new MarshallingSerializer());
        SERIALIZER_MAP.put(SerializeType.ProtoBufSerializer, new ProtoBufSerializer());
        SERIALIZER_MAP.put(SerializeType.ProtoStuffSerializer, new ProtoStuffSerializer());
        SERIALIZER_MAP.put(SerializeType.ThriftSerializer, new ThriftSerializer());
    }


    /**
     * 序列化
     *
     * @param obj 序列化对象
     * @param serializeType 序列化类型
     * @param <T>
     * @return
     */
    public static <T> byte[] serialize(T obj, String serializeType) {
        SerializeType serialize = SerializeType.queryByType(serializeType);
        if (serialize == null) {
            throw new RuntimeException("serialize is null");
        }

        ISerializer serializer = SERIALIZER_MAP.get(serialize);
        if (serializer == null) {
            throw new RuntimeException("serialize error");
        }

        try {
            return serializer.serialize(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 反序列化
     *
     * @param data
     * @param clazz
     * @param serializeType
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] data, Class<T> clazz, String serializeType) {

        SerializeType serialize = SerializeType.queryByType(serializeType);
        if (serialize == null) {
            throw new RuntimeException("serialize is null");
        }
        ISerializer serializer = SERIALIZER_MAP.get(serialize);
        if (serializer == null) {
            throw new RuntimeException("serialize error");
        }

        try {
            return serializer.deserialize(data, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String str = "123";
        byte[] serializer = serialize(str , SerializeType.DefaultJavaSerializer.getSerializeType());
        System.out.println(serializer);
       String deseralizer = deserialize(serializer,String.class,SerializeType.DefaultJavaSerializer.getSerializeType());
        System.out.println(deseralizer);
    }
}
