package kw.bitbops.listener.message;

public class EnterMessage {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EnterMessage{" +
                "id=" + id +
                '}';
    }
}
