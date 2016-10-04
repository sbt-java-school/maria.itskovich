package ru.sbt.homeworks.jmm;

import java.util.concurrent.Callable;
/*
Задача, бросающая MyException, унаследованный от RuntimeException
 */
public class TaskException implements Callable {
    @Override
    public Object call() throws Exception {

            throw new MyException();

    }
}
