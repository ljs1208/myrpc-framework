package com.json.myrpc.serializer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author jasonLu
 * @date 08/02/2018 0008 11:06
 * @Description:XML 序列化
 */
public class XmlSerializer implements ISerializer {
    private static final XStream xStream = new XStream(new DomDriver());
    @Override
    public <T> byte[] serialize(T obj) {

        return xStream.toXML(obj).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        String xml = new String(data);
        return (T) xStream.fromXML(xml);
    }
}
