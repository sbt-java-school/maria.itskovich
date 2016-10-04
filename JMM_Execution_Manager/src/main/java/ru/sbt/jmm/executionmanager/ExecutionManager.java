package ru.sbt.jmm.executionmanager;

public interface ExecutionManager {

    Context execute(Runnable callback, Runnable... tasks);
}
