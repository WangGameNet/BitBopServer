package kw.test.listener;

import com.esotericsoftware.kryonet.Connection;

import kw.test.bean.BaseMessage;
import kw.test.server.TypeWorldServer;

public class ServerListener <T extends BaseMessage> extends AbstractListener<T> {
    protected TypeWorldServer server;
    public ServerListener(Class clazz) {
        super(clazz);
        this.server = TypeWorldServer.getTypeWorldServer();
    }



    public void sendTcp(int id,Object object){
        server.sendTcp(id,object);
    }

    public void sendUdp(int id,Object obj){
        server.sendToUDP(id,obj);
    }

    @Override
    public void accept(Connection conncetion, T elem) {

    }
}