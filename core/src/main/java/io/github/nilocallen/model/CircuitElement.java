package io.github.nilocallen.model;

public class CircuitElement {
    public final String name;
    public final String type; // "AND", "RAM", ...

    public CircuitElement(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
