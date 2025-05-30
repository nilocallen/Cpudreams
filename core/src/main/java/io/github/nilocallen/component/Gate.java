package io.github.nilocallen.component;

public abstract class Gate {
    protected boolean[] inputs;
    protected boolean output;

    public Gate(int inputCount) {
        inputs = new boolean[inputCount];
    }

    public void setInput(int index, boolean value){
        inputs[index] = value;
    }

    public boolean getOutput() {
        return output;
    }

    public abstract void evaluate();
}
