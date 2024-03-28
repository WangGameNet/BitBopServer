package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.ArrayMap;
import com.esotericsoftware.kryonet.Connection;

import kw.bitbops.listener.message.AddRoomMessage;
import kw.bitbops.listener.message.RoomInfoMessage;
import kw.test.listener.ServerListener;

public class AddRoomListener extends ServerListener<AddRoomMessage> {
    private ArrayMap<Integer, RoomInfoMessage> roomInfoMap;
    public AddRoomListener(ArrayMap<Integer, RoomInfoMessage> roomInfoMap) {
        super(AddRoomMessage.class);
        this.roomInfoMap = roomInfoMap;
    }

    @Override
    public void accept(Connection conncetion, AddRoomMessage elem) {
        RoomInfoMessage message = roomInfoMap.get(elem.getTarConnectId());
        if (message!=null){
            message.setOther(conncetion.getID());
            message.setActive(true);
        }
    }
}
