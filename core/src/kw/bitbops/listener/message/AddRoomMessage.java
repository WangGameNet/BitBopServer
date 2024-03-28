package kw.bitbops.listener.message;

import kw.test.bean.BaseMessage;

public class AddRoomMessage extends BaseMessage {
    private int tarConnectId;
    private int fromConnectId;

    public int getTarConnectId() {
        return tarConnectId;
    }

    public void setTarConnectId(int tarConnectId) {
        this.tarConnectId = tarConnectId;
    }

    public int getFromConnectId() {
        return fromConnectId;
    }

    public void setFromConnectId(int fromConnectId) {
        this.fromConnectId = fromConnectId;
    }
}
