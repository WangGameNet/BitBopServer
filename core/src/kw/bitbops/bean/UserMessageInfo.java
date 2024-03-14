package kw.bitbops.bean;

import com.esotericsoftware.kryonet.Connection;

public class UserMessageInfo {
    private Connection connection;
    private Object object;

    public UserMessageInfo(Connection connection, Object object) {
        this.connection = connection;
        this.object = object;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "connection=" + connection +
                ", object=" + object +
                '}';
    }
}
