package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryonet.Connection;
import com.kw.gdx.utils.log.NLog;

import kw.bitbops.bean.UserInfo;
import kw.bitbops.listener.message.HelloMessage;
import kw.test.listener.ServerListener;
import kw.test.server.TypeWorldServer;

/**
 * 发送hello的用户的链接存储起来
 * 发送exit的链接删除
 */
public class HelloMessageListener extends ServerListener<HelloMessage> {
    private Array<UserInfo> connects;
    public HelloMessageListener(Array<UserInfo> connects) {
        super(HelloMessage.class);
        this.connects = connects;
    }

    @Override
    public void accept(Connection conncetion, HelloMessage elem) {
        NLog.i("hello message "+elem.toString());
        UserInfo userInfo = new UserInfo(conncetion,conncetion.getID());
        connects.add(userInfo);
        HelloMessage message = new HelloMessage();
        message.setId(conncetion.getID());
        message.setMsg("注册成功！");
        sendUdp(conncetion.getID(),message);
        NLog.i("register success :"+message.toString());
    }

}
