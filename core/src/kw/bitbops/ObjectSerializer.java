//package kw.bitbops;
//
//import com.badlogic.gdx.utils.Array;
//import com.esotericsoftware.kryo.Kryo;
//import com.esotericsoftware.kryo.Serializer;
//import com.esotericsoftware.kryo.io.Input;
//import com.esotericsoftware.kryo.io.Output;
//
//import kw.bitbops.message.DingStatusMessage;
//
//public class ObjectSerializer extends Serializer<Array<DingStatusMessage>> {
//    {
//        setImmutable(true);
//    }
//
//    @Override
//    public void write(Kryo kryo, Output output, Array<DingStatusMessage> object) {
//        for (DingStatusMessage message : object) {
//            output.write(message.);
//        }
//    }
//
//    @Override
//    public Array<DingStatusMessage> read(Kryo kryo, Input input, Class<Array<DingStatusMessage>> type) {
//        return null;
//    }
//}
