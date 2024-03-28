package kw.bitbops.listener.abst;

import com.badlogic.gdx.utils.ArrayMap;
import com.esotericsoftware.kryonet.Connection;


import kw.bitbops.listener.message.ExitMessage;
import kw.bitbops.listener.message.ExitRoomMessage;
import kw.bitbops.listener.message.RoomInfoMessage;
import kw.test.listener.AbstractListener;

public class ExitRoomListener extends AbstractListener<ExitMessage> {
    public ExitRoomListener(ArrayMap<Integer, RoomInfoMessage> roomInfoMap) {
        super(ExitRoomMessage.class);
    }

    @Override
    public void accept(Connection conncetion, ExitMessage elem) {

    }
}
