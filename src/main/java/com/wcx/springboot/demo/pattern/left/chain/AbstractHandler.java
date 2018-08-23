package com.wcx.springboot.demo.pattern.left.chain;

public abstract class AbstractHandler {

    /**
     * 下一个处理器
     */
    private AbstractHandler next;

    /**
     * 当前处理级别
     */
    private int level;

    public AbstractHandler(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    void handle(Request request) {
        if (this.getLevel() == request.getType()) {
            echo(request);
        } else {
            if (next != null) {
                next.handle(request);
            }
        }
    }

    public void setNext(AbstractHandler next) {
        this.next = next;
    }

    public abstract int getLevle();

    /**
     * 实际处理的类
     *
     * @param request
     */
    public abstract void echo(Request request);
}
