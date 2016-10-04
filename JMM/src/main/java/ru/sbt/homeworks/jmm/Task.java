package ru.sbt.homeworks.jmm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class Task<T> {
    private Callable<? extends T> callable;
    private Map<Callable, T> cash;   // кеш значений
    private Exception exception;
    private Object monitor;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
        this.cash = new HashMap<Callable, T>();
        this.monitor = new Object();
    }

    public T get() throws Exception {
        if (exception != null) {   // если один поток при выполнении задачи бросил исключение, остальные потоки сразу будут выкидывать его же при попытке выполнения этой задачи, не выполняя ее
            System.out.println("Такое исключение уже было");
            throw exception;
        } else if (cash.containsKey(callable)) {   // если один раз эту задачу уже выполнили, значение берется из кеша, не заходя в синхронизированную область
            System.out.println("Из кеша");
            return cash.get(callable);
        } else {
            synchronized (monitor) {
                try {
                    cash.put(callable, callable.call());
                } catch (MyException e) {
                    exception = e;
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return callable.call();
        }
    }

}

