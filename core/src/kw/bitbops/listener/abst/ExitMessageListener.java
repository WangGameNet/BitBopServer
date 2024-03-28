package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.esotericsoftware.kryonet.Connection;

import kw.bitbops.bean.RoomInfo;
import kw.bitbops.bean.UserInfo;
import kw.bitbops.listener.message.ExitMessage;
import kw.test.bean.BaseMessage;
import kw.test.listener.ServerListener;

public class ExitMessageListener extends ServerListener<ExitMessage> {
    private Array<UserInfo> connects;
    private ArrayMap<Integer,RoomInfo> roomInfoArrayMap;
    public ExitMessageListener(Array<UserInfo> connects, ArrayMap<Integer, RoomInfo> roomInfoMap) {
        super(ExitMessage.class);
        this.connects = connects;
        this.roomInfoArrayMap = roomInfoMap;
    }

    @Override
    public void accept(Connection conncetion, ExitMessage elem) {
        super.accept(conncetion, elem);
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
        if (userInfo != null) {
            connects.removeValue(userInfo, false);
        }
        roomInfoArrayMap.removeKey(connection.getID());
    }
}
