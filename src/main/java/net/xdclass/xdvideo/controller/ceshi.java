package net.xdclass.xdvideo.controller;

import net.xdclass.xdvideo.service.SomeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ceshi {
    public static void main(String[] args) {

        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        SomeService some = ac.getBean("proxyFactoryBean", SomeService.class);
        //System.out.println(some);
       // System.out.println("************");
        String obj = some.doSome("aaa");
        System.out.println(obj);
        System.out.println("----------");
        some.say();
    }
}
