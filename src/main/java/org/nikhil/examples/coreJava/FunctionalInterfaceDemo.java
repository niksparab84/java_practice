package org.nikhil.examples.coreJava;

public class FunctionalInterfaceDemo {

    // Functional interface with a single abstract method
    // Functional interface with a method for joining two values
    // What is a functional interface?
    // A functional interface is an interface that contains exactly one abstract method.
    // It can have multiple default or static methods, but only one abstract method.
    // Functional interfaces can be used as the assignment target for a lambda expression or method reference.
    // They are a key feature of Java 8 and later versions, enabling functional programming in Java.

    @FunctionalInterface
    interface Joiner<T> {
        /**
         * Joins two values of type T and returns the result.
         *
         * @param a the first value
         * @param b the second value
         * @return the joined result
         */
        public abstract T join(T a, T b);
        //T split(T a, T b); // This is not an abstract method, so it won't break the functional interface contract
        public static <T> Joiner<T> of(Joiner<T> joiner) {
            return joiner;
        }

        // Default methods can be added to a functional interface
        default T defaultJoin(T a, T b) {
            return join(a, b);
        }
    }

    // Example of a functional interface
    static class IntegerJoinerImpl implements Joiner<Integer> {
        @Override
        public Integer join(Integer a, Integer b) {
            return a + b;
        }

        //@Override
        public Integer split(Integer a, Integer b) {
            return a - b;
        }

        @Override
        public Integer defaultJoin(Integer a, Integer b) {
            return Joiner.super.defaultJoin(a, b);
        }
    }

    static class StringJoinerImpl implements Joiner<String> {

        @Override
        public String join(String a, String b) {
            return a + b;
        }

        //@Override
        public String split(String a, String b) {
            return "";
        }

        @Override
        public String defaultJoin(String a, String b) {
            return Joiner.super.defaultJoin(a, b);
        }
    }

    public static void main(String[] args) {
        // Using a lambda expression to implement the functional interface
        //Joiner<Integer> joiner = (a, b) -> a + b;
        int sum = new IntegerJoinerImpl().join(5, 10);
        System.out.println("Sum: " + sum);

        String joinedString = new StringJoinerImpl().join("Hello", " World");
        System.out.println("Joined String: " + joinedString);
    }

    // Java 8 introduced the concept of functional interfaces,
    // which are interfaces with a single abstract method.
    // They can be used with lambda expressions to create instances of the interface.
    // Functional interfaces are a key feature of Java 8 and later versions,
    // enabling functional programming in Java.
    // Other Java8 features introduced include:
    // 1. Lambda Expressions: Allow you to write concise, inline implementations of functional interfaces.
    // 2. Method References: Provide a way to refer to methods by their names, making code more readable.
    // 3. Streams API: Introduces a new abstraction for processing sequences of elements, allowing for functional-style operations on collections.
    // 4. Default Methods: Allow interfaces to have method implementations, enabling backward compatibility.
    // 5. Optional: A container object which may or may not contain a value, used to avoid null checks.
    // 6. New Date and Time API: Provides a more comprehensive and user-friendly way to handle dates and times.
    // 7. Functional Interfaces: Interfaces with a single abstract method, enabling functional programming.
    // 8. CompletableFuture: A class that represents a future result of an asynchronous computation, allowing for non-blocking programming.
    // 9. New APIs for Concurrency: Enhancements to the java.util.concurrent package, including new classes and methods for better concurrency support.
    // 10. Type Annotations: Allow annotations to be applied to types, enabling better type checking and validation.
    // 11. New APIs for I/O: Enhancements to the java.nio.file package, providing better support for file I/O operations.
    // 12. Nashorn JavaScript Engine: A new lightweight JavaScript engine that allows you to run JavaScript code on the JVM.
    // 13. New APIs for Networking: Enhancements to the java.net package, providing better support for networking operations.
    // 14. New APIs for Security: Enhancements to the java.security package, providing better support for security operations.
    // 15. New APIs for Reflection: Enhancements to the java.lang.reflect package, providing better support for reflection operations.
    // 16. New APIs for Annotations: Enhancements to the java.lang.annotation package, providing better support for annotations.

    // Java 11 features:
    // 1. Local-Variable Syntax for Lambda Parameters: Allows the use of var in lambda expressions.
    // 2. String Methods: New methods like isBlank(), lines(), strip(), and repeat() for better string manipulation.
    // 3. New File Methods: New methods like isSameFile(), readString(), and writeString() for better file handling.
    // 4. New Collection Methods: New methods like toArray(IntFunction) and copyOf() for better collection handling.
    // 5. New HTTP Client: A new HTTP client API that supports HTTP/2 and WebSocket.
    // 6. New Optional Methods: New methods like ifPresentOrElse() and or() for better handling of Optional values.
    // 7. New Pattern Matching for instanceof: Allows the use of pattern matching with instanceof operator.
    // 8. New Switch Expressions: Allows the use of switch expressions for better control flow.
    // 9. New Unicode Support: Support for new Unicode characters and scripts.

    // 10. New Garbage Collector: A new garbage collector called ZGC (Z Garbage Collector) for better performance.
    // ZGC is a low-latency garbage collector that aims to provide high throughput and low pause times.
    // It is designed to handle large heaps and is suitable for applications that require low-latency garbage collection.
    // It is available as an experimental feature in Java 11 and later versions.
    // How to use ZGC:
    // To use ZGC, you need to enable it by adding the following JVM option:
    // -XX:+UseZGC
    // You can also configure ZGC with additional options, such as:
    // -XX:ZAllocationSpikeTolerance=<value> - Controls the allocation spike tolerance.
    // -XX:ZUncommitDelay=<value> - Controls the delay before uncommitting memory.
    // How did ZGC improve performance?
    // ZGC improves performance by reducing pause times during garbage collection,
    // allowing applications to continue running with minimal interruptions.
    // Advantages of ZGC:
    // - Low pause times: ZGC aims to keep pause times below 10 milliseconds, even for large heaps.
    // - High throughput: ZGC is designed to provide high throughput for applications with large heaps.
    // - Scalability: ZGC can handle large heaps (up to several terabytes) without significant performance degradation.
    // Advantages over other garbage collectors:
    // - Compared to G1 (Garbage-First) collector, ZGC provides lower pause times and better performance for large heaps.
    // - Compared to CMS (Concurrent Mark-Sweep) collector, ZGC provides better scalability and lower pause times.
    // - Compared to Parallel GC, ZGC provides lower pause times and better performance for large heaps.
    // Disadvantages of ZGC:
    // - ZGC is still an experimental feature and may not be suitable for all applications.
    // - It may require additional tuning and configuration to achieve optimal performance.

    // 11. New APIs for Flight Recorder: A new API for flight recording, which allows you to record and analyze JVM events.
    // 12. New APIs for JFR: New APIs for Java Flight Recorder, which allows you to record and analyze JVM events.
    // 13. New APIs for JDK Flight Recorder: New APIs for JDK Flight Recorder, which allows you to record and analyze JVM events.
    // 14. New APIs for JDK Mission Control: New APIs for JDK Mission Control, which allows you to monitor and analyze JVM performance.
    // 15. New APIs for JDK VisualVM: New APIs for JDK VisualVM, which allows you to monitor and analyze JVM performance.

    // Java 17 features:
    // 1. Sealed Classes: Allows you to restrict which classes can extend or implement a class or interface.
    // 2. Pattern Matching for instanceof: Allows you to use pattern matching with the instanceof operator.
    // 3. New macOS Rendering Pipeline: A new rendering pipeline for macOS that uses the Apple Metal framework.
    // 4. New macOS AWT Backend: A new AWT backend for macOS that uses the Apple Metal framework.
    // 5. New macOS Swing Backend: A new Swing backend for macOS that uses the Apple Metal framework.
    // 6. New macOS JavaFX Backend: A new JavaFX backend for macOS that uses the Apple Metal framework.
    // 7. New macOS JDK Backend: A new JDK backend for macOS that uses the Apple Metal framework.
    // 8. New macOS JDK Mission Control Backend: A new JDK Mission Control backend for macOS that uses the Apple Metal framework.
    // 9. New macOS JDK VisualVM Backend: A new JDK VisualVM backend for macOS that uses the Apple Metal framework.
    // 10. New macOS JDK Flight Recorder Backend: A new JDK Flight Recorder backend for macOS that uses the Apple Metal framework.
    // 11. New macOS JDK Mission Control Flight Recorder Backend: A new JDK Mission Control Flight Recorder backend for macOS that uses the Apple Metal framework.
    // 12. New macOS JDK VisualVM Flight Recorder Backend: A new JDK VisualVM Flight Recorder backend for macOS that uses the Apple Metal framework.
    // 13. New macOS JDK Flight Recorder Mission Control Backend: A new JDK Flight Recorder Mission Control backend for macOS that uses the Apple Metal framework.
    // 14. New macOS JDK VisualVM Mission Control Backend: A new JDK VisualVM Mission Control backend for macOS that uses the Apple Metal framework.
    // 15. New macOS JDK Flight Recorder VisualVM Backend: A new JDK Flight Recorder VisualVM backend for macOS that uses the Apple Metal framework.
    // 16. New macOS JDK Mission Control VisualVM Backend: A new JDK Mission Control VisualVM backend for macOS that uses the Apple Metal framework.
    // 17. New macOS JDK Flight Recorder Mission Control VisualVM Backend: A new JDK Flight Recorder Mission Control VisualVM backend for macOS that uses the Apple Metal framework.
    // 18. New macOS JDK Mission Control Flight Recorder VisualVM Backend: A new JDK Mission Control Flight Recorder VisualVM backend for macOS that uses the Apple Metal framework.
    // 19. New macOS JDK Flight Recorder Mission Control Flight Recorder Backend: A new JDK Flight Recorder Mission Control Flight Recorder backend for macOS that uses the Apple Metal framework.
    // Other major features in Java 17 include:
    // 1. New macOS AWT Backend: A new AWT backend for macOS that uses the Apple Metal framework.
    // 2. New macOS Swing Backend: A new Swing backend for macOS that uses the Apple Metal framework.
    // 3. New macOS JavaFX Backend: A new JavaFX backend for macOS that uses the Apple Metal framework.
    // Other major enhancements in Garbage Collection (GC) in Java 17 include:
    // 1. New Garbage Collector: A new garbage collector called ZGC (Z Garbage Collector) for better performance.
    // ZGC improvements in Java 17:
    // - Improved performance for large heaps and low-latency applications.
    // - Enhanced support for concurrent class unloading and memory management.
    // - Better handling of allocation spikes and memory pressure.
    // - Improved pause time predictability and reduced memory footprint.
    
    // 2. New Garbage Collector: A new garbage collector called Shenandoah for better performance.
    // 3. New Garbage Collector: A new garbage collector called G1 (Garbage-First) for better performance.
    // 4. New Garbage Collector: A new garbage collector called CMS (Concurrent Mark-Sweep) for better performance.
    // 5. New Garbage Collector: A new garbage collector called Parallel GC for better performance.
    // 6. New Garbage Collector: A new garbage collector called Serial GC for better performance.




}
