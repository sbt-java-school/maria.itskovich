package ru.sbt.homeworks.jmm.executionmanager;

public interface ExecutionManager {

    Context execute(Runnable callback, Runnable... tasks);
}
