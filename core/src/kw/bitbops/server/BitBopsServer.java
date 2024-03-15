package kw.bitbops.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import kw.bitbops.GameLogic;
import kw.bitbops.bean.DingBean;
import kw.bitbops.bean.UserInfo;
import kw.bitbops.bean.UserMessageInfo;
import kw.bitbops.message.DingStatusMessage;
import kw.bitbops.message.EnterMessage;
import kw.bitbops.message.ExitMessage;
import kw.bitbops.message.HelloMessage;
import kw.bitbops.message.HitDingMessage;
import kw.bitbops.message.OutDingMessage;

public class BitBopsServer {
    private static final int TCP_PORT = 1234;
    private static final int UDP_PORT = 1235;
    private Queue<UserInfo> userInfoBeanArray;
    private Queue<UserMessageInfo> userMessageArray;
    private Array<Connection> lostConnect;
    private Server server;
    private int userId = 0;
    private GameLogic logic;

    public BitBopsServer(){
        this.logic = new GameLogic();
        this.userInfoBeanArray = new LinkedList<>();
        this.userMessageArray = new LinkedList<>();
        this.lostConnect = new Array<>();
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

    public void update() {
        for (int i = 0; i < userMessageArray.size(); i++) {
            UserMessageInfo userMessageInfo = userMessageArray.poll();
            Object object = userMessageInfo.getObject();
            if (object instanceof EnterMessage){
                dealEnterMessage(userMessageInfo, (EnterMessage) object);

            }else if (object instanceof ExitMessage){
                dealExitMessage((ExitMessage) object);

            }else if (object instanceof HitDingMessage){
                dealHitDingMessage((HitDingMessage)object);

            }else if (object instanceof OutDingMessage){
                dealOutDingMessage((OutDingMessage)object);

            }
        }

        UserInfo temp = null;
        for (Connection connection : lostConnect) {
            for (UserInfo info : userInfoBeanArray) {
                if (info.getConnection() == connection) {
                    temp = info;
                    break;
                }
            }
        }
        userInfoBeanArray.remove(temp);
        //move
        logic.update(Gdx.graphics.getDeltaTime());
        Array<DingStatusMessage> array = logic.genSendMessage();
        for (UserInfo info : userInfoBeanArray) {
            sendUDP(info.getConnection(),array);
        }
    }

    private void dealOutDingMessage(OutDingMessage object) {
        System.out.println("user out ding ---------");
        DingBean dingBean = logic.outDing();
        object.setId(dingBean.getDingId());
        object.setPox(dingBean.getPox());
        object.setPoy(dingBean.getPoY());
        for (UserInfo info : userInfoBeanArray) {
            sendUDP(info.getConnection(),object);
        }
    }

    private void dealHitDingMessage(HitDingMessage object) {
        System.out.println("user hit ding ---------");
        logic.hitDing();
        for (UserInfo info : userInfoBeanArray) {
            sendUDP(info.getConnection(),object);
        }
    }

    private void dealExitMessage(ExitMessage object) {
        ExitMessage exitMessage = object;
        int id = exitMessage.getId();
        UserInfo info = null;
        for (UserInfo userInfo : userInfoBeanArray) {
            int id1 = userInfo.getId();
            if(id == id1){
                info = userInfo;
                break;
            }
        }
        userInfoBeanArray.remove(info);
    }

    private void dealEnterMessage(UserMessageInfo userMessageInfo, EnterMessage object) {
        EnterMessage enterMessage = object;
        enterMessage.setId(userId++);
        userInfoBeanArray.add(new UserInfo(userMessageInfo.getConnection(),enterMessage.getId()));
        HelloMessage helloMessage = new HelloMessage();
        helloMessage.setId(enterMessage.getId());
        helloMessage.setMsg("你的序号为："+helloMessage.getId());
        sendUDP(userMessageInfo.getConnection(),helloMessage);
    }

    private void sendUDP(Connection connection, Object object) {
        server.sendToUDP(connection.getID(),object);
    }
}
