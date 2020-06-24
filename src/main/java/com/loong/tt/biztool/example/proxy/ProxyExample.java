package com.loong.tt.biztool.example.proxy;

import com.loong.tt.biztool.example.proxy.model.DemoB;
import com.loong.tt.biztool.example.proxy.model.DemoImplA;
import com.loong.tt.biztool.example.proxy.model.IDemoA;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Proxy;

/**
 * Created by Albert on 2020/6/24.
 */
public class ProxyExample {

    /**
     * java jdk 动态代理实现，被代理类必须实现接口
     *
     * @return 代理对象
     */
    public static Object jdkProxy(Class clazz) {
        Object proxyObject = Proxy.newProxyInstance(
                clazz.getClassLoader(),
                clazz.getInterfaces(),
                (proxy, method, args) -> {
                    //do before
                    Object result = method.invoke(clazz.newInstance(), args);
                    //do after
                    return result;
                }
        );
        return proxyObject;
    }

    /**
     * cglib 动态代理实现，被代理类不必实现接口
     *
     * @return 代理对象
     */
    public static Object cgligProxy(Class clazz) {
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(clazz);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            //do before
            Object result = methodProxy.invokeSuper(o, objects);
            //do after
            return result;
        });

        return enhancer.create();
    }

    public static void main(String[] args) {
        IDemoA demoImplA = (IDemoA) jdkProxy(DemoImplA.class);
        demoImplA.doWhat("jdk 动态代理");

        DemoB demoB = (DemoB) cgligProxy(DemoB.class);
        demoB.doWhat("cglib 动态代理");
    }
}
