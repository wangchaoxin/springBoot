package com.wcx.springboot.demo.pattern.command;

import java.util.List;

/**
 * 可以组合多个command
 */
public class ComposeCommand implements Command {
    private List<Command> commands;

    public ComposeCommand(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    @Override
    public void undo() {

    }
}
