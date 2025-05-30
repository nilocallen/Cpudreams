package io.github.nilocallen.screen.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.nilocallen.CpuDreams;

public class ApplicationScreen implements Screen {

    private final CpuDreams cpuDreams;
    private Stage uiStage;
    private Skin skin;

    public ApplicationScreen(CpuDreams cpuDreams) {
        this.cpuDreams = cpuDreams;
        this.uiStage = new Stage(cpuDreams.uiViewport, cpuDreams.batch);
        Gdx.input.setInputProcessor(uiStage); // For input

        skin = new Skin(Gdx.files.internal("orange/skin/uiskin.json"));
        setupUI();
    }

    private void setupUI() {
        Table root = new Table();
        root.setDebug(true);
        root.setFillParent(true);
        root.top().left(); // Align the table itself
        uiStage.addActor(root);

        Table toolbox = new Table();
        toolbox.setBackground(createColorDrawable(Color.WHITE));
        toolbox.top().left(); // Align contents
        toolbox.defaults().pad(5).width(100).height(40);
        for(int i = 0; i < 10; i++) {
            toolbox.add(new TextButton("AND", skin));
            toolbox.add(new TextButton("OR", skin));
            toolbox.add(new TextButton("NOT", skin));
        }

        ScrollPane scrollPane = new ScrollPane(toolbox);
        scrollPane.setScrollingDisabled(false, true);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollbarsOnTop(true);
        scrollPane.setOverscroll(false, false);
        scrollPane.setForceScroll(true, false);

        Table scrollContainer = new Table();
        scrollContainer.padLeft(20).padRight(20).top().left();
        scrollContainer.add(scrollPane).expandX().fillX();

        root.add(scrollContainer).expandX().fillX().top().left();
    }



    private Drawable createColorDrawable(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegionDrawable(new TextureRegion(texture));
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
        ScreenUtils.clear(Color.DARK_GRAY);
        cpuDreams.uiViewport.apply(true);

        uiStage.act(delta);
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        uiStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        uiStage.dispose();
    }
}
