package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.ArrayMap;
import com.esotericsoftware.kryonet.Connection;
import com.kw.gdx.utils.log.NLog;

import kw.bitbops.listener.message.AddRoomMessage;
import kw.bitbops.listener.message.RoomInfoMessage;
import kw.test.listener.AbstractListener;

public class AddRoomListener extends AbstractListener<AddRoomMessage> {
    public AddRoomListener(ArrayMap<Integer, RoomInfoMessage> roomInfoMap) {
        super(AddRoomMessage.class);
    }

    @Override
    public void accept(Connection conncetion, AddRoomMessage elem) {
        NLog.i(elem.toString());

    }
}
