package kw.bitbops.listener.message;

public class OutDingMessage {
    private int id;
    private float pox;
    private float poy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OutDingMessage(){

    }

    public OutDingMessage(float pox, float poy) {
        this.pox = pox;
        this.poy = poy;
    }

    public float getPox() {
        return pox;
    }

    public void setPox(float pox) {
        this.pox = pox;
    }

    public float getPoy() {
        return poy;
    }

    public void setPoy(float poy) {
        this.poy = poy;
    }

    @Override
    public String toString() {
        return "OutDingMessage{" +
                "pox=" + pox +
                ", poy=" + poy +
                '}';
    }
}