package org.nikhil.examples.coreJava;

public class FunctionalInterfaceDemo {

    public Integer join(Integer a, Integer b) {
        return a + b;
    }

    public String join(String a, String b) {
        return a + b;
    }

    // Functional interface with a single abstract method
    // Functional interface with a method for joining two values
    // What is a functional interface?
    // A functional interface is an interface that contains exactly one abstract method.
    // It can have multiple default or static methods, but only one abstract method.
    // Functional interfaces can be used as the assignment target for a lambda expression or method reference.
    // They are a key feature of Java 8 and later versions, enabling functional programming in Java.
    @FunctionalInterface
    interface Joiner<T> {
        T join(T a, T b);
    }

    // Example of a functional interface
    static class IntegerJoinerImpl implements Joiner<Integer> {

        @Override
        public Integer join(Integer a, Integer b) {
            return a + b;
        }
    }

    static class StringJoinerImpl implements Joiner<String> {

        @Override
        public String join(String a, String b) {
            return a + b;
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

}
