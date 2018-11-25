package com.wcx.springboot.demo.xiangxue.interview.money_transfer;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class OrderServiceImpl implements OrderService {
    @Override
    public boolean addOrder() {

        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateOrder() {

    }
}
