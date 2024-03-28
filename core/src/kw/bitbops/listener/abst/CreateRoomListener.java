package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.ArrayMap;
import com.esotericsoftware.kryonet.Connection;
import com.kw.gdx.utils.log.NLog;

import kw.bitbops.bean.RoomInfo;
import kw.test.listener.ServerListener;

public class CreateRoomListener extends ServerListener<RoomInfo> {
    private ArrayMap<Integer,RoomInfo> arrayMap;
    public CreateRoomListener(ArrayMap<Integer, RoomInfo> arrayMap) {
        super(RoomInfo.class);
        this.arrayMap = arrayMap;
    }

    @Override
    public void accept(Connection conncetion, RoomInfo elem) {
        NLog.i("createRoom info:"+elem);
        RoomInfo info = new RoomInfo();
        info.setRoomName(elem.getRoomName());
        info.setAdmin(conncetion.getID());
        sendUdp(conncetion.getID(),info);
        arrayMap.put(info.getAdmin(),info);
    }


    @Override
    public void disconnected(Connection connection) {
        super.disconnected(connection);
        arrayMap.removeKey(connection.getID());
    }
}
