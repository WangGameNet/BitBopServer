package kw.test.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class WorldServer {
    private static final int TCP_PORT = 1234;
    private static final int UDP_PORT = 1235;
    private Server server;
    /** Queue object to store messages. */
    private Queue<Object> messageQueue;
    /** Connection queue to store connections */
    private Queue<Connection> connectionQueue;

    public WorldServer (){
        server = new Server();

        // primitive arrays
        this.server.getKryo().register(float[].class);

        messageQueue = new LinkedList<>();
        connectionQueue = new LinkedList<>();

        server.addListener(new Listener() {

            @Override
            public void received(Connection connection, Object object) {

                messageQueue.add(object);
                connectionQueue.add(connection);

            }
        });

        server.start();
        try {
            server.bind(TCP_PORT,UDP_PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
