package io.github.nilocallen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.nilocallen.screen.application.ApplicationScreen;
import io.github.nilocallen.screen.menu.MainMenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class CpuDreams extends Game {

    public SpriteBatch batch;
    public BitmapFont font;

    // Builder area
    public FitViewport buildViewport;

    public static final float WORLD_HEIGHT = 8;
    public static final float WORLD_WIDTH = 8;

    // UI Area
    public ScreenViewport uiViewport;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        // UI uses screen coords as skins expect
        uiViewport = new ScreenViewport();

        // Builder uses game units
        buildViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        font.setUseIntegerPositions(false);

        // this.setScreen(new MainMenuScreen(this)); When a menu screen is a good idea
        this.setScreen(new ApplicationScreen(this));
    }

    @Override
    public void resize(int width, int height) {
        uiViewport.update(width, height, true);
        buildViewport.update(width, height, true);

        if (getScreen() != null) getScreen().resize(width, height);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        // Destroy application's resources here.
        batch.dispose();
        font.dispose();
        this.getScreen().dispose();
    }
}
