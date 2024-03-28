package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.esotericsoftware.kryonet.Connection;

import kw.bitbops.listener.message.DeleteRoomMessage;
import kw.bitbops.listener.message.RoomInfoMessage;
import kw.test.listener.ServerListener;

public class DeleteRoomListener extends ServerListener<DeleteRoomMessage> {
    private ArrayMap<Integer,RoomInfoMessage> roomInfoMessageArrayMap;
    public DeleteRoomListener(ArrayMap<Integer, RoomInfoMessage> roomInfoMap) {
        super(DeleteRoomMessage.class);
        this.roomInfoMessageArrayMap = roomInfoMap;
    }

    @Override
    public void accept(Connection conncetion, DeleteRoomMessage elem) {
        super.accept(conncetion, elem);
        roomInfoMessageArrayMap.removeKey(conncetion.getID());
        System.out.println(elem+" delete");
        sendUdp(conncetion.getID(),elem);
    }
}
