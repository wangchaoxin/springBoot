package com.wcx.springboot.demo.midware.log.log4j2;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4j2Example {
    private static final Logger LOGGER = LogManager.getLogger(Log4j2Example.class);
    //static Logger logger = LogManager.getLogger("fileLogger");
    static Logger logger = LogManager.getLogger(Log4j2Example.class);
    public static void main(String[] args)
    {
        LOGGER.debug("Debug Message Logged !!!");
        LOGGER.info("Info Message Logged !!!");
        LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));


        logger.trace("trace level");
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
        logger.fatal("fatal level");

    }
}

