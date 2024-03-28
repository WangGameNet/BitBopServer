package kw.bitbops.listener.abst;

import com.esotericsoftware.kryonet.Connection;

import kw.bitbops.listener.message.DeleteRoomMessage;
import kw.test.bean.BaseMessage;
import kw.test.listener.AbstractListener;
import kw.test.listener.ServerListener;

public class DeleteRoomListener extends AbstractListener<DeleteRoomMessage> {
    public DeleteRoomListener() {
        super(DeleteRoomMessage.class);
    }

    @Override
    public void accept(Connection conncetion, DeleteRoomMessage elem) {

    }
}
