package kw.test.client;


import com.esotericsoftware.kryonet.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import kw.test.bean.BaseMessage;
import kw.test.listener.AbstractListener;

public class TypeClientNet {
    private Client client;

    public TypeClientNet(){
        client = new Client();
        // primitive arrays
        this.client.getKryo().register(float[].class);
        this.client.getKryo().register(BaseMessage.class);
    }

    public void register(Class clazz){
        this.client.getKryo().register(clazz);
    }

    public void connect() throws Exception {
        client.start();
        client.connect(5000, InetAddress.getByName("127.0.0.1"),
                1234, 1235);

    }

    public void sendUDP(Object key) {
        client.sendUDP(key);
    }

    public void stop(){
        client.stop();
    }

    public void reset() throws Exception {
        stop();
        connect();
    }

    private static TypeClientNet typeClientNet;

    public static TypeClientNet getTypeClientNet() {
        if (typeClientNet == null){
            typeClientNet = new TypeClientNet();
        }
        return typeClientNet;
    }

    public void addListener(AbstractListener rigisterListener) {
        client.addListener(rigisterListener);
    }


    public void sendTcp(Object object) {
        client.sendTCP(object);
    }
}
