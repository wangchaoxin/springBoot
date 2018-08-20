package com.wcx.springboot.demo.pattern.state;

public class ConcreteStateB implements State {
    private Context context;

    public ConcreteStateB(Context context) {
        this.context = context;
    }

    /**
     * 在状态实现类内部改变context行为
     */
    @Override
    public void handle() {
        System.out.println("ConcreteStateB.handle");
        /*设置状态转换*/
        context.setState(context.getStateA());
    }
}
