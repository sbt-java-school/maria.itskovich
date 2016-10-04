package ru.sbt.jmm;

import java.util.concurrent.Callable;
/*
Задача, которая не бросает RuntimeException
 */
public class Expression implements Callable {
    @Override
    public Object call() throws Exception {
        return 2 + 9;
    }
}
