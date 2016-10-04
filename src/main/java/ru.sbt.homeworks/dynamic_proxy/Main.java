package ru.sbt.homeworks.dynamic_proxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {

        Calculator calc = new Calculator();
        ICalculator calcproxy = (ICalculator) Proxy.newProxyInstance(Calculator.class.getClassLoader(),
                Calculator.class.getInterfaces(), new CalcInvocationHandler(calc));

        System.out.println(calcproxy.mult(2, 4));
        System.out.println(calcproxy.mult(2, 0));
        System.out.println(calcproxy.mult(2, 4));

        System.out.println(calcproxy.sum(1, 4));
        System.out.println(calcproxy.sum(1, 4));

    }
}

