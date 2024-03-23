package kw.bitbops.bean;

import com.esotericsoftware.kryonet.Connection;

import kw.bitbops.listener.message.BaseMessage;

public class RoomInfo extends BaseMessage {
    private String roomName;
    private int admin;
    private int other;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "RoomInfo{" +
                "roomName='" + roomName + '\'' +
                ", admin=" + admin +
                ", other=" + other +
                '}';
    }
}
