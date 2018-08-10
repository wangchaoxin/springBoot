package com.wcx.springboot.demo.pattern.command;

public interface Command {
    void execute();

    /**
     * 撤销操作
     */
    void undo();
}
