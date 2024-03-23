package kw.bitbops.listener;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import kw.bitbops.listener.message.BaseMessage;

public abstract class AbstractListener<T extends BaseMessage> extends Listener
        implements ListenerConsumer<T> {
    protected int connectid;
    private Class clazz;
    private Server server;
    public AbstractListener(Class clazz){
        this.clazz = clazz;
    }

    @Override
    public void received(Connection connection, Object object) {
        super.received(connection, object);
        connectid = connection.getID();
        if (object.getClass().equals(clazz)){
            accept(connection, (T) object);
        }
    }

    public void setServer(Server server) {
        this.server = server;
    }

    protected void sendTcp(int id,Object o){
        server.sendToTCP(id,o);
    }

    protected void sendUdp(int id,Object o){
        server.sendToUDP(id,o);
    }
}
