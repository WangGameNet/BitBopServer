package kw.bitbops.listener.message;

import com.badlogic.gdx.utils.Array;

import kw.test.bean.BaseMessage;

public class RoomListMessage extends BaseMessage {
    private Array<RoomInfoMessage> array;
    private int type = 0;

    public RoomListMessage(){
        array = new Array<>();
    }

    public void setArray(Array<RoomInfoMessage> array) {
        this.array = array;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void addRoomInfo(RoomInfoMessage roomInfo){
        array.add(roomInfo);
    }

    public Array<RoomInfoMessage> getArray() {
        return array;
    }
}
