package com.wcx.springboot.demo.pattern.command;

/**
 * command实现类
 */
public class ACommand implements Command {

    private Recevier recevier;

    public ACommand(Recevier recevier) {
        this.recevier = recevier;
    }

    /**
     * 实际调用的方法
     */
    @Override
    public void execute() {
        recevier.action();
    }

    @Override
    public void undo() {

    }
}
