package kw.test.server;

import com.badlogic.gdx.Game;

public class WordServerGame extends Game {

    @Override
    public void create() {
        WorldServer server = new WorldServer();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }


    @Override
    public void dispose() {
        super.dispose();
    }
}
