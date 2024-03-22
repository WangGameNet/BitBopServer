package kw.bitbops.listener;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import kw.bitbops.message.BaseMessage;

public abstract class AbstractListener<T extends BaseMessage> extends Listener
        implements ListenerConsumer<T> {

    private Class clazz;
    public AbstractListener(Class clazz){
        this.clazz = clazz;
    }

    @Override
    public void received(Connection connection, Object object) {
        super.received(connection, object);
        if (object.getClass().equals(clazz)){
            accept(connection, (T) object);
        }
    }
}
