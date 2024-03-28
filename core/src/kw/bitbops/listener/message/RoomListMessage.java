package kw.bitbops.listener.message;

import com.badlogic.gdx.utils.Array;

import kw.bitbops.bean.RoomInfo;
import kw.test.bean.BaseMessage;

public class RoomListMessage extends BaseMessage {
    private Array<RoomInfo> array;

    public RoomListMessage(){
        array = new Array<>();
    }

    public void addRoomInfo(RoomInfo roomInfo){
        array.add(roomInfo);
    }
}
