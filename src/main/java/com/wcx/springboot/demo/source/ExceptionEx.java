package com.wcx.springboot.demo.source;

import org.apache.zookeeper.KeeperException;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * exception
 */
public class ExceptionEx {

    static String getCodeMessage(Code code) {
        switch (code) {
            case OK:
                return "ok";
            case SYSTEMERROR:
                return "system error";
            default:
                return "Unknown error " + code;
        }
    }

    private Code code;

    public ExceptionEx(Code code) {
        this.code = code;
    }

    public int getCode() {
        return code.code;
    }

    public void setCode(int code) {
        this.code = Code.get(code);
    }

    public static KeeperException create(int code) {
        return create(Code.get(code));
    }

    public static KeeperException create(Code code) {
        switch (code) {
            case SYSTEMERROR:
                return new SystemErrorException();
            default:
                throw new RuntimeException();
        }
    }


    public interface CodeDeprecated {
        int Ok = 0;
        int SystemError = -1;
    }

    public static enum Code implements CodeDeprecated {
        OK(Ok),
        SYSTEMERROR(SystemError);
        private static final Map<Integer, Code> lookup
                = new HashMap<Integer, Code>();

        static {
            for (Code c : EnumSet.allOf(Code.class))
                lookup.put(c.code, c);
        }

        private final int code;

        Code(int code) {
            this.code = code;
        }

        public static Code get(int code) {
            return lookup.get(code);
        }
    }

    public static class SystemErrorException extends KeeperException {
        public SystemErrorException() {
            super(KeeperException.Code.APIERROR);
        }
    }
}
