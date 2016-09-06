package ru.sbt.homeworks.parametrizedMap;

public class Car<P> {
    private int id;
    private int capacity;
    private P param;

    public Car(int id, int capacity, P param) {
        this.id = id;
        this.capacity = capacity;
        this.param = param;
    }

    public P getParam() {
        return param;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", param=" + param +
                '}';
    }

}


