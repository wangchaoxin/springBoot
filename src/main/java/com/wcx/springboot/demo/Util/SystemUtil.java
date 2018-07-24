package com.wcx.springboot.demo.Util;

import org.apache.commons.lang3.StringUtils;

public class SystemUtil {

    public static final String WINDOWS = "windows";

    public static String getSystemName() {
        return System.getProperty("os.name");
    }

    public static boolean isSystemWindows() {
        String os = System.getProperty("os.name");
        if (StringUtils.isEmpty(os)) {
            return false;
        }
        return os.toLowerCase().contains(WINDOWS);
    }

    public static void main(String[] args) {
        System.out.println(getSystemName());
    }
}
