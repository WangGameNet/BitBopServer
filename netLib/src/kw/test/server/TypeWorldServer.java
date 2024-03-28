package kw.test.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import java.util.LinkedList;
import kw.test.bean.BaseMessage;
import kw.test.listener.AbstractListener;

public class TypeWorldServer {
    private static final int TCP_PORT = 1234;
    private static final int UDP_PORT = 1235;
    private Server server;
    private LinkedList<Connection> connections;
    private static TypeWorldServer typeWorldServer;
    public TypeWorldServer(){
        this.connections = new LinkedList<>();
        this.server = new Server();
        // primitive arrays
        this.server.getKryo().register(float[].class);
        this.server.getKryo().register(BaseMessage.class);
    }

    public static TypeWorldServer getTypeWorldServer() {
        if (typeWorldServer == null){
            typeWorldServer = new TypeWorldServer();
        }
        return typeWorldServer;
    }

    /**
     * 用户自定义那些链接是需要加入的
     * @param connection
     */
    public void addConnect(Connection connection){
        connections.add(connection);
    }

    public void removeConnect(Connection connection){
        connections.remove(connection);
    }

    public void register(Class clazz){
        this.server.getKryo().register(clazz);
    }

    public void serverStart(){
        server.start();
        try {
            server.bind(TCP_PORT,UDP_PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop(){
        server.stop();
    }

    public void reset(){
        stop();
        serverStart();
    }

    public void update(){
    }

    public void sendToUDP(int id, Object object) {
        server.sendToUDP(id,object);
    }

    public void addListener(AbstractListener debugListener) {
        server.addListener(debugListener);
    }

    public void sendTcp(int id, Object object) {
        server.sendToTCP(id, object);
    }
}
