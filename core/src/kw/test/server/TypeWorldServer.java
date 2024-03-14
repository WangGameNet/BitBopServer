package kw.test.server;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import kw.test.bean.TypeUserMessage;

public class TypeWorldServer {
    private static final int TCP_PORT = 1234;
    private static final int UDP_PORT = 1235;
    private Server server;
    private Queue<TypeUserMessage> queue;
    private Array<Connection> connections;

    public TypeWorldServer(){
        this.connections = new Array<>();
        this.server = new Server();
        this.queue = new LinkedList<>();
        // primitive arrays
        this.server.getKryo().register(float[].class);
        this.server.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                super.connected(connection);
                connections.add(connection);
                System.out.println(connection.getID()+"--- connected");
            }

            @Override
            public void received(Connection connection, Object object) {

                queue.add(new TypeUserMessage(connection,object));
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
                connections.removeValue(connection,false);
                System.out.println(connection.getID()+"--- connected");
            }
        });

        server.start();
        try {
            server.bind(TCP_PORT,UDP_PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(){
        if (queue.isEmpty()){
            return;
        }
        for (int i = 0; i < queue.size(); i++) {
            TypeUserMessage poll = queue.poll();
            Object object = poll.getObject();
            if (object instanceof String){
                System.out.println((String)(object));
            }else if (object instanceof Integer){
                System.out.println((int)object);
            }
            for (Connection connection : connections) {
                server.sendToUDP(connection.getID(),object);
            }
        }
    }
}
