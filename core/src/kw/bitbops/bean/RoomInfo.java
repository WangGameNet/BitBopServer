package kw.bitbops.bean;

import kw.test.bean.BaseMessage;

public class RoomInfo extends BaseMessage {
    private String roomName;
    private int admin;
    private int other;
    private boolean active;
    private boolean busy;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    @Override
    public String toString() {
        return "RoomInfo{" +
                "roomName='" + roomName + '\'' +
                ", admin=" + admin +
                ", other=" + other +
                ", active=" + active +
                ", busy=" + busy +
                '}';
    }
}
