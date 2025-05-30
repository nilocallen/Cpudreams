package io.github.nilocallen.component;

public class Wire {
    public Gate from;
    public int fromOutputIndex;
    public Gate to;
    public int toInputIndex;

    public void transfer() {
        to.setInput(toInputIndex, from.getOutput());
    }
}
