package kw.bitbops.server;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;

import kw.bitbops.bean.RoomInfo;
import kw.bitbops.listener.AbstractListener;
import kw.bitbops.listener.message.CreateRoomMessage;
import kw.bitbops.listener.message.DingStatusMessage;
import kw.bitbops.listener.message.EnterMessage;
import kw.bitbops.listener.message.ExitMessage;
import kw.bitbops.listener.message.HelloMessage;
import kw.bitbops.listener.message.HitDingMessage;
import kw.bitbops.listener.message.OutDingMessage;
import kw.bitbops.listener.message.RoomListMessage;

public class BitBopsServer {
    private static final int TCP_PORT = 1234;
    private static final int UDP_PORT = 1235;
    private Server server;

    public BitBopsServer(){
        this.server = new Server();
        this.server.getKryo().register(float[].class);
        this.server.getKryo().register(EnterMessage.class);
        this.server.getKryo().register(ExitMessage.class);
        this.server.getKryo().register(HelloMessage.class);
        this.server.getKryo().register(HitDingMessage.class);
        this.server.getKryo().register(OutDingMessage.class);
        this.server.getKryo().register(DingStatusMessage.class);
        this.server.getKryo().register(Object[].class);
        this.server.getKryo().register(Array.class);
        this.server.getKryo().register(CreateRoomMessage.class);
        this.server.getKryo().register(RoomInfo.class);
        this.server.getKryo().register(RoomListMessage.class);
        server.start();
        try {
            server.bind(TCP_PORT,UDP_PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addListener(AbstractListener listener){
        server.addListener(listener);
        listener.setServer(server);
    }


    public void update() {


//        UserInfo temp = null;
//        for (Connection connection : lostConnect) {
//            for (UserInfo info : userInfoBeanArray) {
//                if (info.getConnection() == connection) {
//                    temp = info;
//                    break;
//                }
//            }
//        }
//        userInfoBeanArray.remove(temp);
//        //move
//        logic.update(Gdx.graphics.getDeltaTime());
//        Array<DingStatusMessage> array = logic.genSendMessage();
//        for (UserInfo info : userInfoBeanArray) {
//            sendUDP(info.getConnection(),array);
//        }
//        logic.removeUnless();
    }


    private void sendUDP(Connection connection, Object object) {
        server.sendToUDP(connection.getID(),object);
    }
}
