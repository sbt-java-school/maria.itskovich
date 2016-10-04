package ru.sbt.homeworks.jmm;

public class MyException extends RuntimeException{
    public MyException() {
    }

    @Override
    public String getMessage() {
        return "Исключение";
    }
}
