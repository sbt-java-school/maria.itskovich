package ru.sbt.homeworks.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalcInvocationHandler implements InvocationHandler {
    private Object obj;

    public CalcInvocationHandler(Object obj) {
        this.obj = obj;
    }

    Map caches = new HashMap();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        if (method.isAnnotationPresent(Cache.class)){            // if the method is marked by @Cache
            Map cache = getMap(method);
            List key = Arrays.asList(args);
            Object value = cache.get(key);
            if (value == null && !cache.containsKey(key)) {     // if it is the first time when we call marked method with this arguments
                value = method.invoke(obj, args);
                cache.put(key, value);
            } else {
                System.out.print("From cache: ");
            }
            return value;
        } else {
            return method.invoke(obj, args);
        }
    }

    /**
     * the method that allows to get a cashed map by the method.
     * in this map the key is arguments, the value is the value of the method invoked with these arguments
     * @param method
     * @return cache
     */
    private Map getMap(Method method) {
        Map cache = (Map)caches.get(method);
        if (cache == null) {
            cache = new HashMap();
            caches.put(method,cache);
        }
        return cache;
    }
}

