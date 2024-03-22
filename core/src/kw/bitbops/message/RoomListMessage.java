package kw.bitbops.message;

import com.badlogic.gdx.utils.Array;

import kw.bitbops.bean.RoomInfo;

public class RoomListMessage {
    private Array<RoomInfo> array;

    public RoomListMessage(){
        array = new Array<>();
    }

    public void addRoomInfo(RoomInfo roomInfo){
        array.add(roomInfo);
    }
}
