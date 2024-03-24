package kw.bitbops;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.kw.gdx.utils.log.NLog;

import kw.bitbops.bean.RoomInfo;
import kw.bitbops.bean.UserInfo;
import kw.bitbops.listener.abst.ActiveRoomMessageListener;
import kw.bitbops.listener.abst.AddRoomListener;
import kw.bitbops.listener.abst.CreateRoomListener;
import kw.bitbops.listener.abst.HelloMessageListener;
import kw.bitbops.listener.abst.RoomListMessageListener;
import kw.bitbops.server.BitBopsServer;

public class BitbopsGame extends Game {
    public BitBopsServer server;
    private Array<UserInfo> connects;
    private ArrayMap<Integer, RoomInfo> roomInfoMap;
    public BitbopsGame(){
        connects = new Array<>();
        roomInfoMap = new ArrayMap<>();
        NLog.isLog = true;
    }

    @Override
    public void create() {
        server = new BitBopsServer();
        server.addListener(new HelloMessageListener(connects));
        server.addListener(new CreateRoomListener(roomInfoMap));
        server.addListener(new RoomListMessageListener(roomInfoMap));
        server.addListener(new ActiveRoomMessageListener(roomInfoMap));
        server.addListener(new AddRoomListener(roomInfoMap));
    }

    @Override
    public void render() {
        server.update();
        super.render();
    }
}
