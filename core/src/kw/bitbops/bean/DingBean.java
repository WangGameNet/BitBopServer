package kw.bitbops.bean;

public class DingBean {
    private int dingId;
    private float pox;
    private float poY;
    private int status;  //0 直立  1.左歪  2.右歪 3，打中 ,4 死亡

    public DingBean(int dingId, float pox, float poY) {
        this.dingId = dingId;
        this.pox = pox;
        this.poY = poY;
    }

    public int getDingId() {
        return dingId;
    }

    public void setDingId(int dingId) {
        this.dingId = dingId;
    }

    public float getPox() {
        return pox;
    }

    public void setPox(float pox) {
        this.pox = pox;
    }

    public float getPoY() {
        return poY;
    }

    public void setPoY(float poY) {
        this.poY = poY;
    }

    public void move(float delta) {
        this.pox = pox + delta * 200;
        if (pox>1200){
            setStatus(4);
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
