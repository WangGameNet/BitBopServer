package kw.bitbops;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.kw.gdx.utils.log.NLog;

import java.util.List;
import java.util.ServiceLoader;

import kw.bitbops.bean.UserInfo;
import kw.bitbops.listener.abst.JoinRoomMessageListener;
import kw.bitbops.listener.abst.CreateRoomListener;
import kw.bitbops.listener.abst.DeleteRoomListener;
import kw.bitbops.listener.abst.ExitMessageListener;
import kw.bitbops.listener.abst.HelloMessageListener;
import kw.bitbops.listener.abst.LevelRoomMessageListener;
import kw.bitbops.listener.abst.RoomListMessageListener;
import kw.bitbops.listener.message.JoinRoomMessage;
import kw.bitbops.listener.message.DeleteRoomMessage;
import kw.bitbops.listener.message.ExitMessage;
import kw.bitbops.listener.message.HelloMessage;
import kw.bitbops.listener.message.LevelRoomMessage;
import kw.bitbops.listener.message.RoomInfoMessage;
import kw.bitbops.listener.message.RoomListMessage;
import kw.bitbops.scannerImp.ModuleClassScannerImpl;
import kw.test.scanner.ModuleClassScanner;
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
        server.register(Array.class);
        server.register(Object[].class);
        server.register(HelloMessage.class);
        server.register(ExitMessage.class);
        server.register(RoomInfoMessage.class);
        server.register(RoomListMessage.class);
        server.register(DeleteRoomMessage.class);
        server.register(JoinRoomMessage.class);
        server.register(LevelRoomMessage.class);

        server.addListener(new HelloMessageListener(connects));
        server.addListener(new ExitMessageListener(connects,roomInfoMap));
        server.addListener(new CreateRoomListener(connects,roomInfoMap));
        server.addListener(new RoomListMessageListener(connects,roomInfoMap));
        server.addListener(new DeleteRoomListener(roomInfoMap));
        server.addListener(new JoinRoomMessageListener(roomInfoMap));
        server.addListener(new LevelRoomMessageListener(roomInfoMap));

    }

    @Override
    public void render() {
        server.update();
        super.render();
    }
}
