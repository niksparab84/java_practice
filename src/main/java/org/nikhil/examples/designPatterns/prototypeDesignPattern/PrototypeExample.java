package org.nikhil.examples.designPatterns.prototypeDesignPattern;

// Prototype interface for creating clones of objects
interface Shape {
    Shape clone();
    void draw();
}

// Concrete implementation of the Prototype interface
class Circle implements Shape {
    private String color;

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public Shape clone() {
        return new Circle(this.color);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " color Circle");
    }
}

// Another concrete implementation of the Prototype interface
class Square implements Shape {
    private String color;

    public Square(String color) {
        this.color = color;
    }

    @Override
    public Shape clone() {
        return new Square(this.color);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " color Square");
    }
}

// Client code to demonstrate the Prototype pattern
class ShapeClient {
    private Shape shapePrototype;

    public ShapeClient(Shape shapePrototype) {
        this.shapePrototype = shapePrototype;
    }

    public Shape createShape() {
        return shapePrototype.clone();
    }
}

public class PrototypeExample {
    public static void main(String[] args) {
        // Create a Circle prototype
        Shape circlePrototype = new Circle("Red");

        // Create a ShapeClient with the Circle prototype
        ShapeClient circleClient = new ShapeClient(circlePrototype);

        // Use the prototype to create a new shape (a red circle).
        Shape redCircle = circleClient.createShape();
        redCircle.draw();

        // Create a Square prototype
        Shape squarePrototype = new Square("Blue");

        // Create a ShapeClient with the Square prototype
        ShapeClient squareClient = new ShapeClient(squarePrototype);

        // Use the prototype to create a new shape (a blue square).
        Shape blueSquare = squareClient.createShape();
        blueSquare.draw();
    }

    // The Prototype pattern allows for the creation of new objects by copying existing ones,
    // which can be more efficient than creating new instances from scratch.
    // This example demonstrates how to use the Prototype pattern with different shapes.
    // The Circle and Square classes implement the Shape interface, which defines the clone method.
    // The ShapeClient class uses a Shape prototype to create new shapes.
    // The main method demonstrates creating a red circle and a blue square using the prototypes.
    // The Prototype pattern is useful when the cost of creating a new instance is expensive,
    // and you want to avoid the overhead of initializing a new object from scratch.
    // The Prototype pattern is particularly useful in scenarios where you need to create
    // many similar objects with slight variations, as it allows for easy cloning and modification.
    // This example can be extended to include more shapes or different properties,
    // demonstrating the flexibility and reusability of the Prototype pattern.
    // The Prototype pattern is a creational design pattern that allows for the creation of new objects
    // by copying existing ones, which can be more efficient than creating new instances from scratch.
}
