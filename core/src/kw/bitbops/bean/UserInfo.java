package kw.bitbops.bean;


import com.esotericsoftware.kryonet.Connection;

public class UserInfo {
    private int id;
    private Connection connection;

    public UserInfo(Connection connection, int id){
        this.id = id;
        this.connection = connection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
