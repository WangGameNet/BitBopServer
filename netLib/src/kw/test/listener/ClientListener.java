package kw.test.listener;

import com.esotericsoftware.kryonet.Connection;

import kw.test.bean.BaseMessage;
import kw.test.client.TypeClientNet;

public class ClientListener<T extends BaseMessage> extends AbstractListener<T>{
    private TypeClientNet clientNet;
    public ClientListener(Class clazz) {
        super(clazz);
        clientNet = TypeClientNet.getTypeClientNet();
    }

    public void sendTcp(Object object){
        clientNet.sendTcp(object);
    }

    @Override
    public void accept(Connection conncetion, T elem) {

    }
}
