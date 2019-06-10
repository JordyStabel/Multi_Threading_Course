package com.s3.multi_threading_couse;

import java.math.BigInteger;

public class _4_ThreadInterrupting {

    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(new BlockingTask());
//        thread.start();
//        thread.interrupt();

        Thread thread2 = new Thread(new LongComputationTask(new BigInteger("2"), new BigInteger("1000000")));

        thread2.setDaemon(true);
        thread2.start();
        thread2.sleep(100);
        thread2.interrupt();
    }

    private static class BlockingTask implements Runnable {

        @Override
        public void run() {
            // Block the task for a long time
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread");
            }
        }
    }

    private static class LongComputationTask implements Runnable {
        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }
}
