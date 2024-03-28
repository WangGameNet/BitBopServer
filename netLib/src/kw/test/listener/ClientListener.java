package kw.test.listener;

import com.esotericsoftware.kryonet.Connection;

import kw.test.bean.BaseMessage;
import kw.test.client.TypeClientNet;

public class ClientListener<T extends BaseMessage> extends AbstractListener<T>{
    private TypeClientNet clientNet;
    public ClientListener(Class clazz) {
        super(clazz);
        clientNet = TypeClientNet.getTypeClientNet();
        clientNet.register(clazz);
    }

    public void sendTcp(Object object){
        clientNet.sendTcp(object);
    }

    public void sendUdp(Object obj){
        clientNet.sendUDP(obj);
    }

    @Override
    public void accept(Connection conncetion, T elem) {

    }
}
