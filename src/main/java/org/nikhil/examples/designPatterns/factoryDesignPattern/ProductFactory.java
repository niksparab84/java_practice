package org.nikhil.examples.designPatterns.factoryDesignPattern;

/*
    * Factory Design Pattern
    * Creational pattern
    * It provides a way to create objects without specifying their exact class.
    * It defines an interface for creating objects, but the specific type of object is determined by the subclass.
    * By using the factory pattern, we can create objects in a centralized way, which makes it easier to manage object creation
    * and reduces code duplication.
 */

// Product interface
interface Product {
    void create();
}

// Concrete Product classes
class ConcreteProductA implements Product {
    @Override
    public void create() {
        System.out.println("ConcreteProductA created");
    }
}

class ConcreteProductB implements Product {
    @Override
    public void create() {
        System.out.println("ConcreteProductB created");
    }
}

// Factory class
public class ProductFactory {
    public static Product createProduct(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        if ("A".equalsIgnoreCase(type)) {
            return new ConcreteProductA();
        } else if ("B".equalsIgnoreCase(type)) {
            return new ConcreteProductB();
        }
        return null;
    }

    // Main class to test the Factory Design Pattern
    public static void main(String[] args) {
        //ProductFactory factory = ProductFactory.createProduct();

        // Create Product A
        Product productA = ProductFactory.createProduct("A");
        productA.create();

        // Create Product B
        Product productB = ProductFactory.createProduct("B");
        productB.create();
    }
}

