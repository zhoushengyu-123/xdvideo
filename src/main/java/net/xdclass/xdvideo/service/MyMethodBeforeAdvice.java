package net.xdclass.xdvideo.service;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

public class  MyMethodBeforeAdvice  implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("目标对象执行之前....");
        Object obj = invocation.proceed();
        // 目标对象的调用
        System.out.println("目标对象执行之后....");
        if(obj != null && obj instanceof String){
            return ((String) obj).toUpperCase(); }
        return obj;
    }
}
