package com.json.myrpc.serializer;

import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author jasonLu
 * @date 08/02/2018 0008 11:07
 * @Description:
 */
public class AvroSerializer implements ISerializer {


    @Override
    public <T> byte[] serialize(T obj) {
        if( !(obj instanceof SpecificRecordBase)){
            throw new UnsupportedOperationException("不支持的类型");
        }
        DatumWriter userDatumWriter = new SpecificDatumWriter(obj.getClass());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(outputStream, null);

        try {
            userDatumWriter.write(obj, binaryEncoder);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputStream.toByteArray();
    }


    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        if (!SpecificRecordBase.class.isAssignableFrom(clazz)) {
            throw new UnsupportedOperationException("not supported clazz type");
        }
        try {
            DatumReader userDatumReader = new SpecificDatumReader(clazz);
            BinaryDecoder binaryDecoder = DecoderFactory.get().directBinaryDecoder(new ByteArrayInputStream(data), null);
            return (T) userDatumReader.read(clazz.newInstance(), binaryDecoder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
