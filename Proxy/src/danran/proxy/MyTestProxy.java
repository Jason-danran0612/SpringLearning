package danran.proxy;

import java.lang.reflect.Method;

/**
 * @Classname MyTestProxy
 * @Description TODO
 * @Date 2020/4/17 20:49
 * @Created by Jason
 */
public class MyTestProxy implements MyInvocationHandler {
    private Object target;//被代理的对象的引用
//    返回代理对象实例
    public Object getInstance(Object target) throws Exception {
        this.target = target;
        Class<?> clazz = target.getClass();
        return MyProxy.newProxyInstance(new MyClassLoader(),clazz.getInterfaces(),this);
    }
    @Override
//    代理方法
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeAciton();
        Object obj = method.invoke(this.target,args);
        afterAction();
        return obj;
    }

    private void afterAction() {
        System.out.println("执行after....");
    }

    private void beforeAciton() {
        System.out.println("执行before...");
    }
}
