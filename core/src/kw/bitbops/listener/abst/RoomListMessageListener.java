package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryonet.Connection;

import kw.bitbops.bean.UserInfo;
import kw.bitbops.listener.message.RoomInfoMessage;
import kw.bitbops.listener.message.RoomListMessage;
import kw.test.listener.AbstractListener;
import kw.test.listener.ServerListener;
import kw.test.server.TypeWorldServer;

/**
 * 响应用户获取房间列表请求
 */
public class RoomListMessageListener extends AbstractListener<RoomListMessage> {
    private Array<UserInfo> connects;
    private ArrayMap<Integer, RoomInfoMessage> roomInfoArrayMap;
    public RoomListMessageListener(Array<UserInfo> connects, ArrayMap<Integer, RoomInfoMessage> roomInfoMap) {
        super(RoomListMessage.class);
        TypeWorldServer.getTypeWorldServer().register(Array.class);
        TypeWorldServer.getTypeWorldServer().register(Object[].class);
        TypeWorldServer.getTypeWorldServer().register(RoomListMessage.class);
        this.roomInfoArrayMap = roomInfoMap;
        this.connects = connects;
    }

    @Override
    public void accept(Connection conncetion, RoomListMessage elem) {
        RoomListMessage roomListMessage = new RoomListMessage();
        for (ObjectMap.Entry<Integer, RoomInfoMessage> integerRoomInfoEntry : roomInfoArrayMap) {
            RoomInfoMessage value = integerRoomInfoEntry.value;
            roomListMessage.addRoomInfo(value);
        }
        if (elem.getType() == 0){
            TypeWorldServer.getTypeWorldServer().sendToUDP(conncetion.getID(),roomListMessage);
        }else {
            for (UserInfo connect : connects) {
                TypeWorldServer.getTypeWorldServer().sendToUDP(connect.getId(),roomListMessage);
            }
        }
    }
}
