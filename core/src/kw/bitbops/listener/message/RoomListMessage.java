package kw.bitbops.listener.message;

import com.badlogic.gdx.utils.Array;

import kw.test.bean.BaseMessage;

public class RoomListMessage extends BaseMessage {
    private Array<RoomInfoMessage> array;

    public RoomListMessage(){
        array = new Array<>();
    }

    public void addRoomInfo(RoomInfoMessage roomInfoMessage){
        array.add(roomInfoMessage);
    }
}
