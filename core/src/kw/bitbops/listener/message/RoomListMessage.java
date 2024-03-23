package kw.bitbops.listener.message;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.compression.lzma.Base;

import kw.bitbops.bean.RoomInfo;

public class RoomListMessage extends BaseMessage {
    private Array<RoomInfo> array;

    public RoomListMessage(){
        array = new Array<>();
    }

    public void addRoomInfo(RoomInfo roomInfo){
        array.add(roomInfo);
    }
}
