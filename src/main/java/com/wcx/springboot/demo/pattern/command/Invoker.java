package com.wcx.springboot.demo.pattern.command;

/**
 * 调用者,实际调用方法的类,包含command字段
 */
public class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }
    public void invoke(){
        command.execute();
    }
}
