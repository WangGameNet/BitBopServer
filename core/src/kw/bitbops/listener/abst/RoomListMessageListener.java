package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryonet.Connection;

import kw.bitbops.bean.RoomInfo;
import kw.bitbops.listener.message.RoomListMessage;
import kw.test.listener.AbstractListener;
import kw.test.listener.ServerListener;

/**
 * 响应用户获取房间列表请求
 */
public class RoomListMessageListener extends ServerListener<RoomListMessage> {

    private ArrayMap<Integer,RoomInfo> roomInfoArrayMap;
    public RoomListMessageListener(ArrayMap<Integer, RoomInfo> roomInfoMap) {
        super(RoomListMessage.class);
        this.roomInfoArrayMap = roomInfoMap;
    }

    @Override
    public void accept(Connection conncetion, RoomListMessage elem) {
        RoomListMessage roomListMessage = new RoomListMessage();
        for (ObjectMap.Entry<Integer, RoomInfo> integerRoomInfoEntry : roomInfoArrayMap) {
            RoomInfo value = integerRoomInfoEntry.value;
            roomListMessage.addRoomInfo(value);
        }
        sendUdp(conncetion.getID(),roomListMessage);
    }
}
