package kw.bitbops.listener.message;

import kw.test.bean.BaseMessage;

public class JoinRoomMessage extends BaseMessage {
    private int tarConnectId;
    private int fromConnectId;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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
