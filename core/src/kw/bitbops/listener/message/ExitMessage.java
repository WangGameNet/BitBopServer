package kw.bitbops.listener.message;

import kw.test.bean.BaseMessage;

public class ExitMessage extends BaseMessage {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ExitMessage{" +
                "id=" + id +
                '}';
    }
}
