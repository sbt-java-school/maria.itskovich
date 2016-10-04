package ru.sbt.homeworks.superclasses;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Class<Test> t = Test.class;

        printSuperclasses(t);
    }

    /**
     * Метод выводит дерево наследования классов до null
     * @param t
     */

    private static void printSuperclasses(Class<Test> t) {
        Class currentClass = t;
        List<Class> listClasses = new ArrayList<>();
        listClasses.add(t);
        while (currentClass != null) {
            if (currentClass.getSuperclass() == null) {
                listClasses.add(currentClass.getSuperclass()); // находим суперкласс и помещаем его в List
                break;
            }
            listClasses.add(currentClass.getSuperclass());
            currentClass = currentClass.getSuperclass();

        }

        System.out.println(listClasses.get(listClasses.size()-1));
        System.out.println("^");
        System.out.println("|");

        for (int i = listClasses.size()-2; i >= 0; i--) {
            System.out.println(listClasses.get(i).getSimpleName()); // распечатываем дерево
            if (listClasses.get(i) == t) {
                break;
            }
            System.out.println("^");
            System.out.println("|");
        }


    }


}


