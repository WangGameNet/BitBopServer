package kw.bitbops;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import java.util.UUID;

import kw.bitbops.bean.DingBean;
import kw.bitbops.message.DingStatusMessage;
import kw.bitbops.server.BitBopsServer;

public class GameLogic {
    private int  dingIndex;
    private Array<DingBean> dingBeanArray;
    private Vector2 start;

    public GameLogic(){
        this.dingBeanArray = new Array<>();
        start = new Vector2();

    }

    public void outDing(){
        dingBeanArray.add(new DingBean(dingIndex,0,0));
    }

    public void hitDing(){
        for (DingBean dingBean : dingBeanArray) {
            if (dingBean.getPox()> start.x && dingBean.getPox()<start.y) {
                //发送
            }
        }
    }

    public void update(float delta){
        for (DingBean dingBean : dingBeanArray) {
            dingBean.move(delta);
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
}
