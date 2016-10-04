package ru.sbt.jmm.executionmanager;


public interface Context {

    int getCompletedTaskCount();

    int getFailedTaskCount();

    int getInterruptedTaskCount();

    void interrupt();

    boolean isFinished();

}
