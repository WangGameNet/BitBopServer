package kw.bitbops.listener.message;

import kw.test.bean.BaseMessage;

public class AddRoomMessage implements BaseMessage {
    private int room;
    private String msg;

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "AddRoomMessage{" +
                "room=" + room +
                ", msg='" + msg + '\'' +
                '}';
    }
}
