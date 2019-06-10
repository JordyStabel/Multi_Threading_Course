package com.s3.multi_threading_couse;

public class Main2 {

    public static void main(String[] args){
        Thread thread = new NewThread();
        thread.start();
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            // Code that will run on a new thread
            // Because Thread itself implements Runnable, we have access to all methods by using: this.
            System.out.println("We are in the new thread: " + this.getName());
        }
    }
}
