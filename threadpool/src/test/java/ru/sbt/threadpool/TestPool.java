package ru.sbt.threadpool;

import org.junit.Test;
import ru.sbt.threadpool.PersonCreator;
import ru.sbt.threadpool.ThreadPool;

import java.util.ArrayList;
import java.util.List;

public class TestPool {
    @Test
    public void test() {

        int n = 20;
        List<String> names = new ArrayList<>(n);
        List<Integer> balances = new ArrayList<>(n);
        List<PersonCreator> persons = new ArrayList<>(n);
        ThreadPool pool = new ThreadPool(5);

        for (int i = 0; i < n; i++) {
            persons.add(new PersonCreator((int) (Math.random()*100), "z" + " " + i));
        }


        for (int i = 0; i < n; i++) {
             pool.execute(persons.get(i));
        }


    }
}
