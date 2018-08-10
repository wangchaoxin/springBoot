package com.wcx.springboot.demo.pattern.command;

public class Client {
    public static void main(String[] args) {
        Recevier recevier=new Recevier();
        ACommand aCommand=new ACommand(recevier);
        Invoker invoker=new Invoker(aCommand);
        invoker.invoke();
    }
}
