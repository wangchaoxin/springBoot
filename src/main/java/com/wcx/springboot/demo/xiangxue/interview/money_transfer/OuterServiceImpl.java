package com.wcx.springboot.demo.xiangxue.interview.money_transfer;

import org.springframework.stereotype.Service;

@Service
public class OuterServiceImpl implements OuterService {
    @Override
    public boolean call() throws InterruptedException {
        Thread.sleep(10000000);
        return true;
    }
}
