package com.s3.multi_threading_couse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _5_FactorialCalculation {

    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(123440L, 0L, 3435L, 35435L, 2324L, 4656L, 23L, 5566L);

        List<FactorialThread> threads = new ArrayList<>();

        /** Create a separated thread for each input value in the list */
        for (long inputNumber : inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        /** Start all the threads */
        for (Thread thread : threads) {
            thread.setDaemon(true);
            thread.start();
        }

        /** Thread join will only return after a thread has finished.
         By passing along a value, it will return regardless whether or not the thread actually finished or not
         Insuring that the main thread will only check all results AFTER all thread are done with their calculation
         */
        for (Thread thread : threads) {
            thread.join(2000);
        }

        /** Check all threads to see if they are done
         If so request the result and print it out
         */
        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }
    }

    public static class FactorialThread extends Thread {
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long n) {
            BigInteger tempResult = BigInteger.ONE;

            for (long i = n; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }
    }
}
