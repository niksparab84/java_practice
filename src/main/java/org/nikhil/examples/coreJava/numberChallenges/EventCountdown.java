package org.nikhil.examples.coreJava.numberChallenges;

import java.util.Scanner;

public class EventCountdown {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter countdown start: ");
        int seconds = sc.nextInt();

        while(seconds > 0) {
            System.out.println("Time left: " + seconds + " seconds");
            try {
                Thread.sleep(1000); // pause for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            seconds--;
        }
        System.out.println("Event started!");
    }
}
