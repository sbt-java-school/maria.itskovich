package ru.sbt.homeworks.jmm;

import org.junit.Test;
import ru.sbt.homeworks.jmm.Expression;
import ru.sbt.homeworks.jmm.MyException;
import ru.sbt.homeworks.jmm.Task;
import ru.sbt.homeworks.jmm.TaskException;

public class TestTask {
    @Test
    public void test() throws InterruptedException {
        TaskException taskException = new TaskException();
        Expression expression = new Expression();

        Task<Object> task1 = new Task<>(expression);
        Task<Object> task2 = new Task<>(taskException);

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(getRunnable(task2));
            thread.start();
        }

        Thread.sleep(5000);
    }

    /*
    Функция, принимающая задачу и возвращающая по ней объект Runnable
     */
    public Runnable getRunnable(Task<Object> task) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(task.get());
                }
                catch (MyException e) {
                    System.out.println("!");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
