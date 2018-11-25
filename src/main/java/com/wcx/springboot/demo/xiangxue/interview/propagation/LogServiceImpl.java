package com.wcx.springboot.demo.xiangxue.interview.propagation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogServiceImpl implements LogService {

    @Override
    /**
     * isolation指定隔离级别，DEFAULT使用数据库默认的隔离级别
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public void addLog() {

    }
}
