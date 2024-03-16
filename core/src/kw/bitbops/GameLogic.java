package kw.bitbops;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import java.util.UUID;

import kw.bitbops.bean.DingBean;
import kw.bitbops.message.DingStatusMessage;
import kw.bitbops.server.BitBopsServer;

public class GameLogic {
    private int dingIndex;
    private Array<DingBean> dingBeanArray;
    private Vector2 good;
    private Vector2 left;
    private Vector2 right;
    private float offSex = 10;
    public GameLogic(){
        this.dingBeanArray = new Array<>();
        good = new Vector2(705-offSex,735-offSex);
        left = new Vector2(735-offSex,770-offSex);
        right = new Vector2(770-offSex,800-offSex);
    }

    public DingBean outDing(){
        DingBean dingBean = new DingBean(dingIndex++, 0, 0);
        dingBeanArray.add(dingBean);
        return dingBean;
    }

    public void hitDing(){
        for (DingBean dingBean : dingBeanArray) {
            if (dingBean.getStatus() == 0) {
                if (dingBean.getPox() >= good.x && dingBean.getPox() <= good.y) {
                    //发送
                    dingBean.setStatus(3);
                } else if (dingBean.getPox() >= left.x && dingBean.getPox() <= left.y) {
                    dingBean.setStatus(1);
                } else if (dingBean.getPox() >= right.x && dingBean.getPox() <= right.y) {
                    dingBean.setStatus(2);
                }
            }
        }
    }


    Array<DingBean> arraytemp = new Array<>();
    public void update(float delta) {
        arraytemp.clear();
        for (DingBean dingBean : dingBeanArray) {
            dingBean.move(delta);
            if (dingBean.getStatus() == 4) {
                arraytemp.add(dingBean);
            }
        }
    }

    public Array<DingStatusMessage> genSendMessage() {
        Array<DingStatusMessage> array = new Array<>();
        for (DingBean dingBean : dingBeanArray) {
            DingStatusMessage message = new DingStatusMessage();
            message.setPox(dingBean.getPox());
            message.setPoY(dingBean.getPoY());
            message.setDingID(dingBean.getDingId());
            message.setStatus(dingBean.getStatus());
            array.add(message);
        }
        return array;
    }

    public void removeUnless() {
        for (DingBean dingBean : arraytemp) {
            dingBeanArray.removeValue(dingBean,false);
        }
    }
}
