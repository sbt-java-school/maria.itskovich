package ru.sbt.homeworks.parametrizedMap;

import java.util.*;

public class CarRegistry<T> {
    Map<T, List<Car>> carRegistry = new HashMap<T, List<Car>>();  // Создаем новую HashMap, где ключ - это нужное нам значение параметра

    public CarRegistry(List<Car> list) {
        for (Car item : list) {
            T type = (T) item.getParam();
            put(type, item);
        }

    }

    /**
     * Метод добавляет машину в карту по данному типу
     * @param type
     * @param item
     */
    public void put(T type, Car item) {
        if (carRegistry.containsKey(type)) {
            carRegistry.get(type).add(item);
        }
        else {
            carRegistry.put(type, new ArrayList<>(Arrays.asList(item)));
        }

    }

    void viewCarByType() {
        System.out.println(carRegistry);

    }

    /**
     * Метод позволяет получить список машин с одни одинаковым параметром
     * @param type
     * @return truckList
     */

    public List getCarRegistryByType(T type) {
        List<Car> truckList = carRegistry.get(type);
        if (truckList == null) {
            throw new IllegalArgumentException("this type was not found");
        }
        return truckList;
    }

}

