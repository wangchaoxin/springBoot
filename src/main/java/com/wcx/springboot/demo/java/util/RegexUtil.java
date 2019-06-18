package com.wcx.springboot.demo.java.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    /**
     * the  + means "one or more times" and \d means "a digit"
     *
     * @param s
     * @return
     */
    public static boolean isConsistOfNumber(String s) {
        String regex = "\\d+";
        return s.matches(regex);
    }
    public void test() {
        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher("");
        if (matcher.matches()) {

        }
    }




    public static void main(String[] args) {
        System.out.println(RegexUtil.isConsistOfNumber("aaefef11111"));
        System.out.println(RegexUtil.isConsistOfNumber("11232323323"));
        System.out.println(RegexUtil.isConsistOfNumber(""));
        System.out.println(RegexUtil.isConsistOfNumber("aaaaaaaefefe"));
    }
}
