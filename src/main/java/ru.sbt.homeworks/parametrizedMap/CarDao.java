package ru.sbt.homeworks.parametrizedMap;

import java.util.Arrays;
import java.util.List;

public class CarDao {
    public List<Car> listCarByMark() {
        return  Arrays.asList(
                new Car<String>(1, 10, "Kamaz"),
                new Car<String>(2, 20, "Uaz"),
                new Car<String>(3, 40, "Uaz"));
    }
    public List<Car> listCarByValue() {
        return  Arrays.asList(
                new Car<Integer>(1, 10, 100),
                new Car<Integer>(3, 30, 100),
                new Car<Integer>(2, 20, 200));

    }
}
