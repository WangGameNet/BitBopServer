package kw.bitbops;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.kw.gdx.BaseGame;

import kw.bitbops.server.BitBopsServer;

public class BitbopsGame extends Game {
    public static BitBopsServer server;


    @Override
    public void create() {
        server = new BitBopsServer();
    }

    @Override
    public void render() {
        server.update();
        super.render();
    }
}
