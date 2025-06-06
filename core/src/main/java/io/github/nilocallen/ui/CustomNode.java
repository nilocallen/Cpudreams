package io.github.nilocallen.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;

public class CustomNode extends Tree.Node<CustomNode, Tree, Actor> {
    public CustomNode(Actor actor) {
        super(actor);
    }
}
