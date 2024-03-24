package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.ArrayMap;
import com.esotericsoftware.kryonet.Connection;
import com.kw.gdx.utils.log.NLog;

import kw.bitbops.bean.RoomInfo;
import kw.bitbops.listener.AbstractListener;
import kw.bitbops.listener.message.AddRoomMessage;

public class AddRoomListener extends AbstractListener<AddRoomMessage> {
    public AddRoomListener(ArrayMap<Integer, RoomInfo> roomInfoMap) {
        super(AddRoomMessage.class);
    }

    @Override
    public void accept(Connection conncetion, AddRoomMessage elem) {
        NLog.i(elem.toString());

    }
}
