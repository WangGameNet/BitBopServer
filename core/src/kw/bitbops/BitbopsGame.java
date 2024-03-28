package kw.bitbops;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.kw.gdx.utils.log.NLog;

import kw.bitbops.bean.UserInfo;
import kw.bitbops.listener.abst.CreateRoomListener;
import kw.bitbops.listener.abst.ExitMessageListener;
import kw.bitbops.listener.abst.HelloMessageListener;
import kw.bitbops.listener.abst.RoomListMessageListener;
import kw.bitbops.listener.message.RoomInfoMessage;
import kw.test.server.TypeWorldServer;

public class BitbopsGame extends Game {
    public TypeWorldServer server;
    private Array<UserInfo> connects;
    private ArrayMap<Integer, RoomInfoMessage> roomInfoMap;
    public BitbopsGame(){
        connects = new Array<>();
        roomInfoMap = new ArrayMap<>();
        NLog.isLog = true;
    }

    @Override
    public void create() {
        server = TypeWorldServer.getTypeWorldServer();
        server.serverStart();
        server.addListener(new HelloMessageListener(connects));
        server.addListener(new ExitMessageListener(connects,roomInfoMap));
        server.addListener(new CreateRoomListener(connects,roomInfoMap));
        server.addListener(new RoomListMessageListener(roomInfoMap));

    }

    @Override
    public void render() {
        server.update();
        super.render();
    }
}
