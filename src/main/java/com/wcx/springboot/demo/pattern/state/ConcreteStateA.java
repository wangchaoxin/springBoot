package com.wcx.springboot.demo.pattern.state;

public class ConcreteStateA implements State {

    private Context context;

    public ConcreteStateA(Context context) {
        this.context = context;
    }

    /**
     * 在状态实现类内部改变context行为
     */
    @Override
    public void handle() {
        System.out.println("ConcreteStateA.handle");
        context.setState(context.getStateB());
    }
}
