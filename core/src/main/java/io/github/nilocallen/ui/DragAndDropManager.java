package io.github.nilocallen.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class DragAndDropManager {
    private final DragAndDrop dragAndDrop;
    private final Skin skin;

    public DragAndDropManager(Skin skin){
        dragAndDrop = new DragAndDrop();
        this.skin = skin;
    }

    public DragAndDrop.Source createDragSource(final Actor actor, final  String elementName) {
        return new DragAndDrop.Source(actor) {
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setObject(elementName);
                payload.setDragActor(new Label(elementName, skin));
                return payload;
            }
        };
    }

    public DragAndDrop getInstance() {
        return dragAndDrop;
    }
}
