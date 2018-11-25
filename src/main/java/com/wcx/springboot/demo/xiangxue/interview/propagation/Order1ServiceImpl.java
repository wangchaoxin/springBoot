package com.wcx.springboot.demo.xiangxue.interview.propagation;

import com.wcx.springboot.demo.xiangxue.interview.money_transfer.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Order1ServiceImpl implements OrderService {

    @Autowired
    private LogService logService;
    @Override
    public boolean addOrder() {

        return true;
    }

    /**
     * 定义传播机制,默认required,如果当前不存在事务，logService也会新起一个事务执行
     */
    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
    @Transactional(propagation = Propagation.MANDATORY)
//    @Transactional(propagation = Propagation.NEVER)
    public void updateOrder() {
        logService.addLog();
    }
}
