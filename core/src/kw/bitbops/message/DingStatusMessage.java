package kw.bitbops.message;

public class DingStatusMessage {
    private int dingID;
    private int status;  //0 直立  1.左歪  2.右歪 3，打中
    private float pox;
    private float poY;

    public int getDingID() {
        return dingID;
    }

    public void setDingID(int dingID) {
        this.dingID = dingID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}
