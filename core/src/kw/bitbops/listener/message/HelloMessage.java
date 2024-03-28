package kw.bitbops.listener.message;

import kw.test.bean.BaseMessage;

public class HelloMessage extends BaseMessage {
    private int id;
    private String msg;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "HelloMessage{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
