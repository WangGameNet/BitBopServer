package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.ArrayMap;
import com.esotericsoftware.kryonet.Connection;
import com.kw.gdx.utils.log.NLog;

import kw.bitbops.bean.RoomInfo;
import kw.bitbops.listener.AbstractListener;
import kw.bitbops.listener.message.ActiveRoomMessage;

/**
 * 用户创建完之后，但是它可以不用，所以我加入一个激活功能，也就是用户创建完成之后等待用户加入
 */
public class ActiveRoomMessageListener extends AbstractListener<ActiveRoomMessage> {
    public ActiveRoomMessageListener(ArrayMap<Integer, RoomInfo> roomInfoMap) {
        super(ActiveRoomMessage.class);
    }

    @Override
    public void accept(Connection conncetion, ActiveRoomMessage elem) {
        NLog.i(elem.toString());

    }
}
