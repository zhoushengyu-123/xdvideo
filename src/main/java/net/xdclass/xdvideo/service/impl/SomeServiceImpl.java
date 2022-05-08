package net.xdclass.xdvideo.service.impl;

import net.xdclass.xdvideo.service.SomeService;
import org.springframework.stereotype.Service;

@Service
public class SomeServiceImpl implements SomeService {
    @Override
    public String doSome(String msg) {
        System.out.println("目标对象doSome执行了..." + msg);
        return "Hello:" + msg;
    }

    @Override
    public void say() {
        System.out.println("目标对象say执行了...");
    }
}
