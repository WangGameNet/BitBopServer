package kw.bitbops.listener.message;

import kw.test.bean.BaseMessage;

public class ExitRoomMessage extends BaseMessage {
    private int roomMainID;
    private int userId;
    private int success;

    public int getRoomMainID() {
        return roomMainID;
    }

    public void setRoomMainID(int roomMainID) {
        this.roomMainID = roomMainID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ExitRoomMessage{" +
                "roomMainID=" + roomMainID +
                ", userId=" + userId +
                ", success=" + success +
                '}';
    }
}
