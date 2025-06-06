package io.github.nilocallen.screen.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.nilocallen.CpuDreams;
import io.github.nilocallen.ui.CustomNode;
import io.github.nilocallen.ui.DragAndDropManager;

public class ApplicationScreen implements Screen {

    private final CpuDreams cpuDreams;
    private Stage uiStage;
    private Skin skin;
    private final DragAndDropManager dragAndDrop = new DragAndDropManager(skin);

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
        root.top().left();
        uiStage.addActor(root);

        /* Toolbox */
        Table toolbox = new Table();
        toolbox.setBackground(createColorDrawable(Color.DARK_GRAY));
        toolbox.top().left();
        toolbox.defaults().pad(5).width(200); // Adjust width as needed

        // Toolbox Tree
        CustomNode defaultFolder = createFolder("Default");
        defaultFolder.add(createToolboxItem("AND"));
        defaultFolder.add(createToolboxItem("NOT"));

        Tree<CustomNode, ?> toolboxTree = new Tree<>(skin);
        toolboxTree.add(defaultFolder);

        // Put the Tree inside a ScrollPane
        ScrollPane toolboxScrollPane = new ScrollPane(toolboxTree, skin);
        toolboxScrollPane.setFadeScrollBars(false);
        toolboxScrollPane.setScrollingDisabled(true, false); // only vertical scroll

        // Add scrollPane to toolbox
        toolbox.add(toolboxScrollPane).grow().pad(20);

        // Add toolbox to root
        root.add(toolbox).expandY().fillY().top().left().width(250); // fixed width
    }

    private CustomNode createFolder(String name){
        Label label = new Label(name, skin);
        label.setColor(Color.SKY);
        label.setFontScale(1.1f);
        label.setAlignment(Align.left);
        label.setTouchable(Touchable.enabled);

        Table wrapper = new Table();
        wrapper.setBackground(createColorDrawable(Color.valueOf("#3C3F41")));
        wrapper.pad(5);
        wrapper.add(label).left().expandX();

        return new CustomNode(wrapper);
    }

    private CustomNode createToolboxItem(String name) {
        TextButton button = new TextButton(name, skin);
        button.getLabel().setFontScale(0.9f);
        button.getLabel().setColor(Color.WHITE);
        button.pad(5);
        button.setBackground(createColorDrawable(Color.valueOf("#3C3F41"))); // Default background
        dragAndDrop.createDragSource(button, name);

        return new CustomNode(button);
    }

    public static Drawable createColorDrawable(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegionDrawable(new TextureRegion(texture));
    }

    public Skin getSkin() {
        return skin;
    }

    /**
     * Called when this screen becomes the current screen for a { Game}.
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
        ScreenUtils.clear(Color.SLATE);
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
     * Called when this screen is no longer the current screen for a {Game}.
     */
    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        uiStage.dispose();
    }
}
