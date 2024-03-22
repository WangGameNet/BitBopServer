package kw.bitbops.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import kw.bitbops.GameLogic;
import kw.bitbops.bean.DingBean;
import kw.bitbops.bean.RoomInfo;
import kw.bitbops.bean.UserInfo;
import kw.bitbops.bean.UserMessageInfo;
import kw.bitbops.deal.BaseMessageDeal;
import kw.bitbops.message.BaseMessage;
import kw.bitbops.message.CreateRoomMessage;
import kw.bitbops.message.DingStatusMessage;
import kw.bitbops.message.EnterMessage;
import kw.bitbops.message.ExitMessage;
import kw.bitbops.message.HelloMessage;
import kw.bitbops.message.HitDingMessage;
import kw.bitbops.message.OutDingMessage;
import kw.bitbops.message.RoomListMessage;

public class BitBopsServer {
    private static final int TCP_PORT = 1234;
    private static final int UDP_PORT = 1235;
    private Server server;
    private Queue<UserMessageInfo> userMessageArray;
    private Array<Connection> lostConnect;


    public BitBopsServer(){
        this.lostConnect = new Array<>();
        this.userMessageArray = new LinkedList<>();
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
        this.server.addListener(new Listener(){
            @Override
            public void received(Connection connection, Object object) {
                super.received(connection, object);
                userMessageArray.add(new UserMessageInfo(connection,object));
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
                lostConnect.add(connection);
            }
        });
        server.start();
        try {
            server.bind(TCP_PORT,UDP_PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BaseMessageDeal<BaseMessage> baseMessageDeal;

    public void setBaseMessageDeal(BaseMessageDeal<BaseMessage> baseMessageDeal) {
        this.baseMessageDeal = baseMessageDeal;
    }

    public void update() {
        for (int i = 0; i < userMessageArray.size(); i++) {
            UserMessageInfo userMessageInfo = userMessageArray.poll();
            Object object = userMessageInfo.getObject();

            if (baseMessageDeal == null) {
                baseMessageDeal = new BaseMessageDeal<>();
            }
            if (object instanceof BaseMessage){
                baseMessageDeal.deal((BaseMessage) object,userMessageInfo);
            }
        }

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
