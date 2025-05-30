package io.github.nilocallen.component;

public class AndGate extends Gate{
    public AndGate(){
        super(2);
    }

    @Override
    public void evaluate() {
        output = inputs[0] && inputs[1];
    }
}
