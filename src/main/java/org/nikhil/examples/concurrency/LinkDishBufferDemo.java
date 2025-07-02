package org.nikhil.examples.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

class Dish {
    private final int dishID;

    public Dish(int dishID) {
        this.dishID = dishID;
    }

    public int getDishID() {
        return dishID;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishID=" + dishID +
                '}';
    }
}

class LinkDishBuffer {
    BlockingQueue<Dish> linkDishBuffer = new LinkedBlockingQueue<>(3);

    public void get() {
        try {
            Dish dish = linkDishBuffer.take();
            System.out.println("Dish recvd for drying: " + dish);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void put(Dish dish) {
        try {
            linkDishBuffer.put(dish);
            System.out.println("Dish washed: " + dish);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public BlockingQueue<Dish> getLinkDishBuffer() {
        return linkDishBuffer;
    }
}

class LinkDishWasherTask implements Runnable {
    LinkDishBuffer linkDishBuffer;

    LinkDishWasherTask(LinkDishBuffer buffer){
        this.linkDishBuffer = buffer;
    }

    @Override
    public void run() {
        for(int i=0; i < 5; i++) {
            Dish dish = new Dish(i);
            this.linkDishBuffer.put(dish);
        }
    }
}

class LinkDishDryerTask implements Runnable {
    LinkDishBuffer linkDishBuffer;

    LinkDishDryerTask(LinkDishBuffer linkDishBuffer) {
        this.linkDishBuffer = linkDishBuffer;
    }

    @Override
    public void run() {
        while(!linkDishBuffer.getLinkDishBuffer().isEmpty()) {
            this.linkDishBuffer.get();
        }
    }
}

public class LinkDishBufferDemo {
    public static void main(String[] args) {
        LinkDishBuffer linkDishBuffer = new LinkDishBuffer();
        try {
            ExecutorService executor = Executors.newFixedThreadPool(2);
            executor.submit(new LinkDishWasherTask(linkDishBuffer));
            Thread.sleep(2000);
            executor.submit(new LinkDishDryerTask(linkDishBuffer));
            executor.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
