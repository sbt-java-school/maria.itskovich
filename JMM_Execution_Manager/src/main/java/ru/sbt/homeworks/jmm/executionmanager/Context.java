package ru.sbt.homeworks.jmm.executionmanager;


public interface Context {

    int getCompletedTaskCount();

    int getFailedTaskCount();

    int getInterruptedTaskCount();

    void interrupt();

    boolean isFinished();

}
