package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryonet.Connection;

import kw.bitbops.listener.message.RoomInfoMessage;
import kw.bitbops.listener.message.RoomListMessage;
import kw.test.listener.AbstractListener;
import kw.test.listener.ServerListener;
import kw.test.server.TypeWorldServer;

/**
 * 响应用户获取房间列表请求
 */
public class RoomListMessageListener extends AbstractListener<RoomListMessage> {

    private ArrayMap<Integer, RoomInfoMessage> roomInfoArrayMap;
    public RoomListMessageListener(ArrayMap<Integer, RoomInfoMessage> roomInfoMap) {
        super(RoomListMessage.class);
        TypeWorldServer.getTypeWorldServer().register(Array.class);
        TypeWorldServer.getTypeWorldServer().register(Object[].class);
        TypeWorldServer.getTypeWorldServer().register(RoomListMessage.class);
        this.roomInfoArrayMap = roomInfoMap;
    }

    @Override
    public void accept(Connection conncetion, RoomListMessage elem) {
        RoomListMessage roomListMessage = new RoomListMessage();
        for (ObjectMap.Entry<Integer, RoomInfoMessage> integerRoomInfoEntry : roomInfoArrayMap) {
            RoomInfoMessage value = integerRoomInfoEntry.value;
            roomListMessage.addRoomInfo(value);
        }
        TypeWorldServer.getTypeWorldServer().sendToUDP(conncetion.getID(),roomListMessage);
    }
}
