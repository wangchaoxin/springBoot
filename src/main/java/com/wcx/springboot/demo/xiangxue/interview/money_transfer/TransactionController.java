package com.wcx.springboot.demo.xiangxue.interview.money_transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("hello")
public class TransactionController {


    @Autowired
    private OuterService outerService;
    @Autowired
    private OrderService orderService;


    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     * 1.transactional 声明注解时会启动一个数据库connection，当客户端连接过多时
     * 超过连接池大小，返回数据库连接错误
     * 耗时操作不会释放连接
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    @Transactional
    public String callAnnotation(
            Model model) throws InterruptedException {

        orderService.addOrder();
        //模拟调用外部接口，耗时操作，会占用数据库连接
        boolean result= outerService.call();
        return "index";
    }

    public String callTemplate() throws InterruptedException {
        //1.执行完成之后释放数据库连接
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                return orderService.addOrder();
            }
        });
        //2.执行耗时操作，不占用数据库连接
        boolean call = outerService.call();

        //3.执行操作完成，更新订单状态
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                if (call) {
                    orderService.updateOrder();
                }
                return "success";
            }
        });
        return "";
    }
}
