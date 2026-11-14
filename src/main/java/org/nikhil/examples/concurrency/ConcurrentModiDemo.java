package org.nikhil.examples.concurrency;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModiDemo extends Thread {

    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        ConcurrentModiDemo tChild = new ConcurrentModiDemo();

        list.add("John");
        list.add("Jane");
        list.add("Jack");
        list.add("Tom");
        list.add("Jerry");

        Iterator<String> iterator = list.iterator();
        tChild.start();

        while (iterator.hasNext()) {
            String name = iterator.next();
            System.out.println("Main thread iterating => " + name);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        list.add("Alice");
        System.out.println("Child thread adding => Alice");
    }
}
