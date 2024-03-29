package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.ArrayMap;
import com.esotericsoftware.kryonet.Connection;

import kw.bitbops.listener.message.JoinRoomMessage;
import kw.bitbops.listener.message.RoomInfoMessage;
import kw.test.listener.ServerListener;

public class JoinRoomMessageListener extends ServerListener<JoinRoomMessage> {
    private ArrayMap<Integer, RoomInfoMessage> roomInfoMap;
    public JoinRoomMessageListener(ArrayMap<Integer, RoomInfoMessage> roomInfoMap) {
        super(JoinRoomMessage.class);
        this.roomInfoMap = roomInfoMap;
    }

    @Override
    public void accept(Connection conncetion, JoinRoomMessage elem) {
        RoomInfoMessage message = roomInfoMap.get(elem.getTarConnectId());
        message.setOther(conncetion.getID());
        elem.setCode(200);
        sendUdp(message.getAdmin(),elem);
        sendUdp(message.getOther(),elem);
    }
}
