package kw.test.server;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import kw.test.bean.UserMessage;

public class WorldServer {
    private static final int TCP_PORT = 1234;
    private static final int UDP_PORT = 1235;
    private Server server;
    private Queue<UserMessage> queue;
    private Array<Connection> connections;

    public WorldServer (){
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
            }

            @Override
            public void received(Connection connection, Object object) {
                System.out.println("server ----- client connect or send");
                queue.add(new UserMessage(connection,object));
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
                connections.removeValue(connection,false);
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
            UserMessage poll = queue.poll();
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
