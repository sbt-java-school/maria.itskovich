package ru.sbt.homeworks.jmm.executionmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutionManagerImpl implements ExecutionManager {
    private int numberThreads;
    private CountDownLatch start = new CountDownLatch(1);
    private CountDownLatch end;

    private AtomicInteger completedTaskCount = new AtomicInteger();
    private AtomicInteger failedTaskCount = new AtomicInteger();
    private AtomicInteger canceledTaskCount = new AtomicInteger();
    private List<Thread> threads;

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        numberThreads = tasks.length;
        end = new CountDownLatch(numberThreads);
        poolThreads(tasks);
        start.countDown();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    end.await();
                    callback.run();
                    System.out.println("callback run");
                } catch (InterruptedException e) {
                }
            }
        }).start();

        return new ContextImpl();
    }

    private void poolThreads(Runnable... tasks) {
        threads = new ArrayList<>(numberThreads);
        for (int i = 0; i < numberThreads; i++) {
            Thread thread = new Thread(new ThreadWorker(tasks[i]));
            threads.add(thread);
            thread.start();
        }
    }

    private class ThreadWorker implements Runnable {
        private final Runnable task;

        private ThreadWorker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            try {
                start.await();
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                task.run();
                completedTaskCount.incrementAndGet();
            } catch (InterruptedException e) {
                canceledTaskCount.incrementAndGet();
            } catch (Exception e) {
                failedTaskCount.incrementAndGet();
            } finally {
                end.countDown();
            }
        }
    }

    private class ContextImpl implements Context {
        @Override
        public int getCompletedTaskCount() {
            return completedTaskCount.get();
        }

        @Override
        public int getFailedTaskCount() {
            return failedTaskCount.get();
        }

        @Override
        public int getInterruptedTaskCount() {
            return canceledTaskCount.get();
        }

        @Override
        public void interrupt() {
            threads.forEach(Thread::interrupt);
        }

        @Override
        public boolean isFinished() {
            return (completedTaskCount.get() + canceledTaskCount.get() == numberThreads);
        }
    }
}

