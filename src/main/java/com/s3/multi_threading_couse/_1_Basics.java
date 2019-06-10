package com.s3.multi_threading_couse;

public class _1_Basics {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                // Code that will run on a new thread
                System.out.println("We are in the new thread: " + Thread.currentThread().getName());
                System.out.println("Current thread priority: " + Thread.currentThread().getPriority());
                throw new RuntimeException("This error is meant to happen...");
            }
        });

        thread.setName("New worker thread");

        thread.setPriority(Thread.MAX_PRIORITY);

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("An error has occurred @ thread: " + thread.getName() + " -> error: " + e.getMessage());
            }
        });

        System.out.println("We are in thread: " + Thread.currentThread().getName() + " BEFORE we started a new thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " AFTER we started a new thread");

        // For set amount of the time, this thread will not be scheduled and thus not use up any CPU
        Thread.sleep(10000);
    }
}
