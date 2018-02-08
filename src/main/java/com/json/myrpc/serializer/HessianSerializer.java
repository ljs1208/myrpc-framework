/**
 * @Author create by jasonLu
 * @Date 08/02/2018 0008
 * @Descriptioon
 **/
package com.json.myrpc.serializer;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author jasonLu
 * @date 08/02/2018 0008 11:06
 * @Description:
 */
public class HessianSerializer implements ISerializer {
    @Override
    public byte[] serialize(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }


        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            HessianOutput ho = new HessianOutput(os);
            ho.writeObject(obj);
            return os.toByteArray();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        if (data == null) {
            throw new NullPointerException();
        }


        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            HessianInput hi = new HessianInput(is);
            return (T) hi.readObject();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
