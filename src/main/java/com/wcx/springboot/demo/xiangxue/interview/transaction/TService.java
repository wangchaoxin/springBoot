package com.wcx.springboot.demo.xiangxue.interview.transaction;

import org.springframework.transaction.annotation.Transactional;

public class TService {

    //Transactional必须写再public方法中，不能是protected和private,不能写在接口中
    //noRollbackFor 指定某些异常不回滚
    //rollbackFor 指定某些异常回滚
    @Transactional(noRollbackFor = NullPointerException.class, rollbackFor = NoSuchMethodException.class)
    void test1() {
        //抛出异常回滚，只有runtime异常可以回滚
        throw new RuntimeException();
    }
}
