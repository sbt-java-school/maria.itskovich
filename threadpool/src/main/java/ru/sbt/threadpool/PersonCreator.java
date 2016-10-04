package ru.sbt.threadpool;

public class PersonCreator implements Runnable {
    private int balance;
    private String name;

    public PersonCreator(int balance, String name) {
        this.balance = balance;
        this.name = name;
    }

    @Override
    public void run() {
            Person person = new Person(name, balance);
            person.increaseBalance(10);
            System.out.println(name + " " + person.balance);

    }
}
