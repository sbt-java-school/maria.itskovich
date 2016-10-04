package ru.sbt.threadpool;

public class Person {
    private String name;
    public int balance;

    public Person(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public void increaseBalance(int increment) {
        this.balance = this.balance + increment;
    }
}
