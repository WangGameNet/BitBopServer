package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryonet.Connection;
import com.kw.gdx.utils.log.NLog;

import kw.bitbops.bean.UserInfo;
import kw.bitbops.listener.AbstractListener;
import kw.bitbops.listener.message.HelloMessage;

/**
 * 发送hello的用户的链接存储起来
 * 发送exit的链接删除
 */
public class HelloMessageListener extends AbstractListener<HelloMessage> {
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
        sendTcp(connectid,message);
        NLog.i("register success :"+message.toString());
    }

    @Override
    public void disconnected(Connection connection) {
        super.disconnected(connection);
        UserInfo userInfo = null;
        for (UserInfo connect : connects) {
            if (connect.getId() == connection.getID()) {
                userInfo = connect;
                break;
            }
        }
        connects.removeValue(userInfo,false);
    }
}
