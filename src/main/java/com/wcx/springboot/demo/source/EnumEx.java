package com.wcx.springboot.demo.source;

/**
 * enum
 */
public enum EnumEx {
    CONNECTED {
        public boolean isConnected() {
            return true;
        }
    },
    READY {
        public boolean isConnected() {
            return true;
        }
    };
    //enum中可以有抽象函数
    public abstract boolean isConnected();
}
