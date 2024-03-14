package kw.test.server;

import com.badlogic.gdx.Game;

public class TypeWordServerGame extends Game {
    TypeWorldServer server;
    @Override
    public void create() {
        server = new TypeWorldServer();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }


    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
        server.update();
    }
}
