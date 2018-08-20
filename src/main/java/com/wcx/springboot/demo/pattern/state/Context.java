package com.wcx.springboot.demo.pattern.state;

public class Context {
    private State stateA;
    private State stateB;
    private State state;
    public Context(){
        stateA = new ConcreteStateA(this);
        stateB = new ConcreteStateB(this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getStateA() {
        return stateA;
    }

    public State getStateB() {
        return stateB;
    }

    /**
     * 外部请求
     */
    public void request(){
        state.handle();
    }
}
