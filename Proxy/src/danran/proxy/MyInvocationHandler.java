package danran.proxy;

import java.lang.reflect.Method;

/**
 * @Classname MyInvocationHandler
 * @Description TODO
 * @Date 2020/4/17 17:31
 * @Created by ASUS
 */
public interface MyInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
