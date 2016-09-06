package ru.sbt.homeworks.dynamic_proxy;

public class Calculator implements ICalculator {

    @Override
    public int mult(int a, int b) {
        return a * b;
    }

    @Override
    public int sum(int a, int b) {
        return a + b;
    }
}
