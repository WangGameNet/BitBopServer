package com.ychstudio.desktop;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import kw.test.server.TypeWordServerGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
        /** Create server. */
        new HeadlessApplication(new TypeWordServerGame(), conf);
    }
}
