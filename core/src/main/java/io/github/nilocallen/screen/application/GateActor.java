package io.github.nilocallen.screen.application;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.nilocallen.component.Gate;

public class GateActor extends Actor {

    private final Gate gate;
    private final ShapeRenderer renderer = new ShapeRenderer();

    public GateActor(Gate gate) {
        this.gate = gate;
        this.setSize(60, 60);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);
        renderer.rect(getX(), getY(), getWidth(), getHeight());
        renderer.end();
        batch.begin();
    }

}
