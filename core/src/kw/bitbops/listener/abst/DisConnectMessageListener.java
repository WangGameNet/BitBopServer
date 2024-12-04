package kw.bitbops.listener.abst;

import com.esotericsoftware.kryonet.Connection;

import kw.bitbops.listener.message.DeleteRoomMessage;
import kw.bitbops.listener.message.DisConnectMessage;
import kw.test.listener.AbstractListener;

public class DisConnectMessageListener extends AbstractListener<DisConnectMessage> {
    public DisConnectMessageListener() {
        super(DisConnectMessage.class);
    }

    @Override
    public void disconnected(Connection connection) {
        super.disconnected(connection);
        System.out.println("level");
    }

    @Override
    public void accept(Connection conncetion, DisConnectMessage elem) {

    }
}
