package com.json.myrpc.enumer;

import org.apache.commons.lang3.StringUtils;

/**
 * @author jasonLu
 * @date 08/02/2018 0008 11:12
 * @Description:序列化枚举类
 */
public enum SerializeType
{
    /**
     * 默认序列化
     */
    DefaultJavaSerializer("DefaultJavaSerializer"),
    /**
     * Hessian序列化
     */
    HessianSerializer("HessianSerializer"),
    /**
     *Json序列化
     */
    JsonSerializer("JsonSerializer"),
    /**
     *ProtoStuff序列化
     */
    ProtoStuffSerializer("ProtoStuffSerializer"),
    /**
     *Xml序列化
     */
    XmlSerializer("XmlSerializer"),
    /**
     *Marshalling序列化
     */
    MarshallingSerializer("MarshallingSerializer"),
    /**
     *Avro序列化
     */
    AvroSerializer("AvroSerializer"),
    /**
     *ProtoBuf序列化
     */
    ProtoBufSerializer("ProtoBufSerializer"),
    /**
     *Thrift序列化
     */
    ThriftSerializer("ThriftSerializer");

    private String serializeType;

    SerializeType(String serializeType)
    {
        this.serializeType = serializeType;
    }

    public static SerializeType queryByType(String serializeType)
    {
        if (StringUtils.isBlank(serializeType))
        {
            return null;
        }
        for (SerializeType serialize : SerializeType.values())
        {
            if (StringUtils.equals(serializeType, serialize.getSerializeType()))
            {
                return serialize;
            }
        }
        return null;
    }

    public String getSerializeType()
    {
        return serializeType;
    }
}
