package com.wcx.springboot.demo.pattern.complex;

public class Simulator {
    public static void main(String[] args) {

        //抽象工厂
        AbstractQuackFactory quackFactory = new QuackFactory();
        //装饰器工厂
        AbstractQuackFactory quackDecoratorFactory = new QuackDocoratorFacotry();


        //QuackDecorator
        Quackable q1 = quackDecoratorFactory.createAQuack();
        Quackable q2 = quackDecoratorFactory.createBQuack();
        Quackable q3 = new QuackDecorator(new QuackAdapter(new AHonk()));

        //组合类
        QuackComposite quackableComposite = new QuackComposite();
        quackableComposite.add(q1);
        quackableComposite.add(q2);
        quackableComposite.add(q3);

        simulate(quackableComposite);

//        simulate(q1);
//        simulate(q2);
//        simulate(q3);

        System.out.println(QuackDecorator.getCount());

    }

    private static void simulate(Quackable quackable) {
        quackable.quack();
    }

}
