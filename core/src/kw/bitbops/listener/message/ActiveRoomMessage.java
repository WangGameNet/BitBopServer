package kw.bitbops.listener.message;

import kw.test.bean.BaseMessage;

public class ActiveRoomMessage implements BaseMessage {
    //激活room
    private boolean activeRoom;
    private int roomId;
    private String msg;

    public boolean isActiveRoom() {
        return activeRoom;
    }

    public void setActiveRoom(boolean activeRoom) {
        this.activeRoom = activeRoom;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ActiveRoomMessage{" +
                "activeRoom=" + activeRoom +
                ", roomId=" + roomId +
                ", msg='" + msg + '\'' +
                '}';
    }
}
