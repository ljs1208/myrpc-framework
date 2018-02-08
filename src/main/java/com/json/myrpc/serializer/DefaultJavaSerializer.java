package com.json.myrpc.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author jasonLu
 * @date 08/02/2018 0008 11:06
 * @Description:
 */
public class DefaultJavaSerializer implements ISerializer {


    @Override
    public <T> byte[] serialize(T obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);

            objectOutputStream.close();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        try
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (T) objectInputStream.readObject();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}
