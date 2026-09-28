package org.nikhil.examples.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintNumbers {

    public static void main(String[] args) {
        for(int i = 1; i <=100; i++) {
            NumberPrinter np = new NumberPrinter(i);
            Thread t = new Thread(np);
            t.start();
        }

        //Using Thread Pool
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        for(int i = 1; i <=100; i++) {
            if(i == 50) {
                System.out.println("Wait");
            }
            NumberPrinter np = new NumberPrinter(i);
            executorService.execute(np);
        }
    }
}

class NumberPrinter implements Runnable {

    private final int numberToPrint;

    public NumberPrinter(int numberToPrint) {
        this.numberToPrint = numberToPrint;
    }

    @Override
    public void run() {
        System.out.println(this.numberToPrint + " " + Thread.currentThread().getName());
    }
}
