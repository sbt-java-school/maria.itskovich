package ru.sbt.jmm;

public class MyException extends RuntimeException{
    public MyException() {
    }

    @Override
    public String getMessage() {
        return "Исключение";
    }
}
