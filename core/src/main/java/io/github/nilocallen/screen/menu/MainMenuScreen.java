package io.github.nilocallen.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.nilocallen.CpuDreams;
import io.github.nilocallen.screen.application.ApplicationScreen;

import static io.github.nilocallen.CpuDreams.WORLD_HEIGHT;
import static io.github.nilocallen.CpuDreams.WORLD_WIDTH;

public class MainMenuScreen implements Screen {

    final CpuDreams cpuDreams;

    public MainMenuScreen(CpuDreams cpuDreams){
        this.cpuDreams = cpuDreams;
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        cpuDreams.uiViewport.apply();
        cpuDreams.batch.setProjectionMatrix(cpuDreams.uiViewport.getCamera().combined);

        cpuDreams.batch.begin();

        cpuDreams.font.draw(cpuDreams.batch, "CPUDreams", WORLD_WIDTH / 2 - 1, WORLD_HEIGHT / 2);
        cpuDreams.font.draw(cpuDreams.batch, "Press anything to continue",  WORLD_WIDTH / 2 - 2, (WORLD_HEIGHT / 2) - 1);

        cpuDreams.batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            cpuDreams.setScreen(new ApplicationScreen(cpuDreams));
        }
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        cpuDreams.uiViewport.update(width, height, true);
    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
