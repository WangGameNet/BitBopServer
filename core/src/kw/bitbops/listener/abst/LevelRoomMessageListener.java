package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.ArrayMap;
import com.esotericsoftware.kryonet.Connection;

import kw.bitbops.listener.message.LevelRoomMessage;
import kw.bitbops.listener.message.RoomInfoMessage;
import kw.test.listener.ClientListener;
import kw.test.listener.ServerListener;

public class LevelRoomMessageListener extends ServerListener<LevelRoomMessage> {
    private ArrayMap<Integer,RoomInfoMessage> arrayMap;
    public LevelRoomMessageListener(ArrayMap<Integer, RoomInfoMessage> roomInfoMap) {
        super(LevelRoomMessage.class);
        this.arrayMap = roomInfoMap;
    }

    @Override
    public void accept(Connection conncetion, LevelRoomMessage elem) {
        super.accept(conncetion, elem);
        RoomInfoMessage message = arrayMap.get(elem.getRoomId());
        message.setOther(0);
        if (elem.isAdmin()){
            arrayMap.removeValue(message,false);
        }else {
            message.setOther(0);
        }
        System.out.println(elem);
        sendUdp(elem.getRoomId(),elem);
        sendUdp(elem.getOtherId(),elem);
    }
}
