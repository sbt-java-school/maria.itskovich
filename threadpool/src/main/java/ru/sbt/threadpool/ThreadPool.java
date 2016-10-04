package ru.sbt.threadpool;

import java.util.LinkedList;
import java.util.concurrent.RunnableFuture;

public class ThreadPool {
    private int numberThreads;
    private LinkedList queue;   // очередь из задач, которые требуется выполнить
    private PoolWorker[] threads;

    public ThreadPool(int numberThreads) {
        this.numberThreads = numberThreads;
        queue = new LinkedList();
        threads = new PoolWorker[numberThreads];

        for (int i = 0; i < numberThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }

    public void execute(Runnable r) {
        synchronized (queue) {
            queue.addLast(r);  // добавляем задачу в конец очереди
            queue.notify();   // оповещаем какой-нибудь поток
        }
    }

    private class PoolWorker extends Thread {

        @Override
        public void run() {
            Runnable r;
            while (true) {
                synchronized (queue) {
                    if (queue.isEmpty()) {
                        try {
                            queue.wait();   // если в очереди нет задач, ждем
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    r = (Runnable) queue.removeFirst();   // достаем из очереди первую задачу
                }
                try {
                    r.run();
                    System.out.println(Thread.currentThread().getName());
                }
                catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
