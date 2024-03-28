package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryonet.Connection;
import com.kw.gdx.utils.log.NLog;

import kw.bitbops.bean.UserInfo;
import kw.bitbops.listener.message.RoomInfoMessage;
import kw.bitbops.listener.message.RoomListMessage;
import kw.test.listener.ServerListener;
import kw.test.server.TypeWorldServer;


/**
 * 用户创建房间
 */
public class CreateRoomListener extends ServerListener<RoomInfoMessage> {
    private ArrayMap<Integer, RoomInfoMessage> arrayMap;
    private Array<UserInfo> connects;
    public CreateRoomListener(Array<UserInfo> connects, ArrayMap<Integer, RoomInfoMessage> arrayMap) {
        super(RoomInfoMessage.class);
        this.arrayMap = arrayMap;
        this.connects = connects;
    }

    @Override
    public void accept(Connection conncetion, RoomInfoMessage elem) {
        NLog.i("createRoom info:"+elem);
        RoomInfoMessage info = new RoomInfoMessage();
        info.setRoomName(elem.getRoomName());
        info.setAdmin(conncetion.getID());
        sendUdp(conncetion.getID(),info);
        arrayMap.put(info.getAdmin(),info);

        RoomListMessage roomListMessage = new RoomListMessage();
        for (ObjectMap.Entry<Integer, RoomInfoMessage> integerRoomInfoEntry : arrayMap) {
            RoomInfoMessage value = integerRoomInfoEntry.value;
            roomListMessage.addRoomInfo(value);
        }
        for (UserInfo connect : connects) {
            TypeWorldServer.getTypeWorldServer().sendToUDP(connect.getId(),roomListMessage);
        }
    }


    @Override
    public void disconnected(Connection connection) {
        super.disconnected(connection);
        arrayMap.removeKey(connection.getID());
    }
}
