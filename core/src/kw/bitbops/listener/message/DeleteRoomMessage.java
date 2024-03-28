package kw.bitbops.listener.message;

import kw.test.bean.BaseMessage;
public class DeleteRoomMessage extends BaseMessage {
    private int room;


    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

}
