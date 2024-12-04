package kw.test.listener;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;

import kw.test.bean.BaseMessage;
import kw.test.server.TypeWorldServer;

public abstract class AbstractListener<T extends BaseMessage> extends Listener implements ListenerConsumer<T> {

    private Class clazz;
    public AbstractListener(Class clazz){
        this.clazz = clazz;
    }

    @Override
    public void received(Connection connection, Object object) {
        super.received(connection, object);
        System.out.println("----------------------------");
        if (object.getClass().equals(clazz)){
            accept(connection, (T) object);
        }
    }
}
