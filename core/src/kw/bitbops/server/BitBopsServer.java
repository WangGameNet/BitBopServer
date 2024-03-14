package kw.bitbops.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import kw.bitbops.bean.UserInfo;
import kw.bitbops.bean.UserMessageInfo;
import kw.bitbops.manager.BitBopManager;
import kw.bitbops.message.EnterMessage;
import kw.bitbops.message.ExitMessage;
import kw.bitbops.message.HelloMessage;

public class BitBopsServer {
    private static final int TCP_PORT = 1234;
    private static final int UDP_PORT = 1235;
    private Queue<UserInfo> userInfoBeanArray;
    private Queue<UserMessageInfo> userMessageArray;
    private Array<UserInfo> plays;
    private Server server;
    private BitBopManager bitBopManager;
    private int userId = 0;
    public BitBopsServer(){
        this.userInfoBeanArray = new LinkedList<>();
        this.userMessageArray = new LinkedList<>();
        this.plays = new Array<>();
        this.server = new Server();
        this.server.getKryo().register(float[].class);
        this.server.getKryo().register(EnterMessage.class);
        this.server.getKryo().register(ExitMessage.class);
        this.server.getKryo().register(HelloMessage.class);
        this.server.addListener(new Listener(){
            @Override
            public void received(Connection connection, Object object) {
                super.received(connection, object);
                userMessageArray.add(new UserMessageInfo(connection,object));
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("delete user info discored--------------");
                        UserInfo temp = null;
                        for (UserInfo userMessageInfo : userInfoBeanArray) {
                            if (userMessageInfo.getConnection() == connection) {
                                temp = userMessageInfo;
                                break;
                            }
                        }
                        if (temp != null){
                            userInfoBeanArray.remove(temp);
                        }
                    }
                });
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
        parseMessage();
    }

    public void parseMessage(){
        for (int i = 0; i < userMessageArray.size(); i++) {
            UserMessageInfo userMessageInfo = userMessageArray.poll();
            Object object = userMessageInfo.getObject();
            if (object instanceof EnterMessage){
                EnterMessage object1 = (EnterMessage) object;
                object1.setId(userId++);
                userInfoBeanArray.add(new UserInfo(userMessageInfo.getConnection(),object1.getId()));
                System.out.println("add userinfo -----------------");
                HelloMessage m = new HelloMessage();
                m.setId(object1.getId());
                m.setMsg("你的序号为："+m.getId());
                sendUDP(userMessageInfo.getConnection(),m);
            }else if (object instanceof ExitMessage){
                ExitMessage object1 = (ExitMessage) object;
                int id = object1.getId();
                UserInfo info = null;
                for (UserInfo userInfo : userInfoBeanArray) {
                    int id1 = userInfo.getId();
                    if(id == id1){
                        info = userInfo;
                        break;
                    }
                }
                System.out.println("delete userinfo -----------------");
                userInfoBeanArray.remove(info);
            }
        }
    }

    private void sendUDP(Connection connection, Object object) {
        server.sendToUDP(connection.getID(),object);
    }
}
