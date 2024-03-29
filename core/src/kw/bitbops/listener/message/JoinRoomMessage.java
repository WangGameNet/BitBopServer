package kw.bitbops.listener.message;

import kw.test.bean.BaseMessage;

public class JoinRoomMessage extends BaseMessage {
    private int roomId;
    private int fromConnectId;
    private int code;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getFromConnectId() {
        return fromConnectId;
    }

    public void setFromConnectId(int fromConnectId) {
        this.fromConnectId = fromConnectId;
    }
}
