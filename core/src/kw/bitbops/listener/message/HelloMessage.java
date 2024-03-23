package kw.bitbops.listener.message;

public class HelloMessage extends BaseMessage{
    private int id;
    private String msg;

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
                '}';
    }
}
