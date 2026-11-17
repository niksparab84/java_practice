package org.nikhil.examples.advancedJava;

import java.util.HashMap;
import java.util.Map;

public class OutOfMemoryErrorDemo {

    public static void gcOverheadDemo() {
        try {
            Map<Integer, String> testMap = new HashMap<>();
            int i = 0;
            while (true) {
                testMap.put(i, "Value" + i);
                i++;
            }
        } catch (Throwable e) {
            System.out.println("Caught Error: " + e.getMessage());
            String msg = String.valueOf(e.getMessage());
            if (msg.contains("Metaspace")) {
                System.out.println("Class loader leak suspected");
            } else if (msg.contains("native thread")) {
                System.out.println("Thread explosion");
            }
            throw e;
        }
    }

    public static void gcOverheadFix() {
        try {
            Map<Integer, String> testMap = new HashMap<>();
            int i = 0;
            while (true) {
                testMap.put(i, "Value" + i);
                i++;
                if ( i % 100000 == 0 ) {
                    System.out.println("Triggering garbage collection...");
                    System.gc(); // Suggest garbage collection
                }
            }
        } catch (Throwable e) {
            System.out.println("Caught Error: " + e.getMessage());
            String msg = String.valueOf(e.getMessage());
            if (msg.contains("Metaspace")) {
                System.out.println("Class loader leak suspected");
            } else if (msg.contains("native thread")) {
                System.out.println("Thread explosion");
            }
            throw e;
        }
    }

    public static void main(String[] args) {
        // gcOverheadDemo();
        //gcOverheadFix();

        String str = new String("abc");
        // it creates a new String object in the heap memory, not in the string pool, so total 2 objects are created, one in heap and one in string pool.

        System.out.println("string == check => " + (str == "abc"));
        System.out.println("string equals check => " + str.equals("abc"));
        System.out.println("string intern check => " + (str.intern() == "abc")); // str.intern() returns pool reference of the string literal "abc"
    }
}

// why System.gc() ?
// System.gc() is a suggestion to the JVM to perform garbage collection. It does not guarantee immediate execution but
// can help in managing memory usage, especially in scenarios where memory pressure is high.
// What does System.gc() do ?
// System.gc() is a method in Java that suggests the Java Virtual Machine (JVM) to perform garbage collection.
// When invoked, it indicates that the JVM should attempt to reclaim memory by identifying and disposing of objects
// that are no longer reachable or not needed by the application. However, it's important to note that calling
// System.gc() does not guarantee that garbage collection will occur immediately or at all, as the JVM has its own
// algorithms and heuristics for managing memory. The actual execution of garbage collection is determined by the
// JVM's internal memory management policies and may vary based on factors such as the current memory usage, the
// type of garbage collector in use and the specific implementation of the JVM.
// In summary, System.gc() serves as a request to the JVM to perform garbage collection, but the decision to execute
// it is ultimately up to the JVM's memory management system.
