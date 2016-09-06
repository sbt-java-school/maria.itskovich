package ru.sbt.homeworks.parametrizedMap;

public class Main {
    public static void main(String[] args) {
        CarDao listCar = new CarDao();
        //  System.out.println(listCar.listCarByMark());
        //  System.out.println(listCar.listCarByValue());

        CarRegistry<Integer> map1 = new CarRegistry<>(listCar.listCarByValue());
        //   map1.viewCarByType();
        System.out.println(map1.getCarRegistryByType(100));

        CarRegistry<String> map = new CarRegistry<>(listCar.listCarByMark());
        System.out.println(map.getCarRegistryByType("Uaz"));

    }
}
