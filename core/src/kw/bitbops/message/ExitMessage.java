package kw.bitbops.message;

public class ExitMessage {
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
