package ru.sbt.homeworks.dynamic_proxy;

public interface ICalculator {

    @Cache
    public int mult(int a, int b);
    public int sum(int a, int b);
}