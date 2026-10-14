package org.nikhil.examples.designPatterns.decoratorDesignPattern;

// Component
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete Component
class BasicCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Basic Coffee";
    }

    @Override
    public double getCost() {
        return 2.0;
    }
}

// Base Decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// Concrete Decorator
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    public String getDescription() {
        return super.getDescription() + " + Milk";
    }

    public double getCost() {
        return super.getCost() + 0.5;
    }
}

// Concrete Decorator
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    public String getDescription() {
        return super.getDescription() + " + Sugar";
    }

    public double getCost() {
        return super.getCost() + 0.2;
    }
}

public class CoffeeDecoratorDemo {
    public static void main(String[] args) {
        // Plain Coffee
        Coffee coffee = new BasicCoffee();
        System.out.println("Coffee: " + coffee.getDescription() + " Costs $" + coffee.getCost());

        // Coffee with milk
        Coffee milkCoffee = new MilkDecorator(new BasicCoffee());
        System.out.println("Coffee: " + milkCoffee.getDescription() + " costs $" + milkCoffee.getCost());

        //Coffee with Sugar and milk
        Coffee sugarMilkCoffee = new SugarDecorator(new MilkDecorator(new BasicCoffee()));
        System.out.println("Coffee: " + sugarMilkCoffee.getDescription() + " costs $" + sugarMilkCoffee.getCost());
    }
}

// This class demonstrates the Decorator Design Pattern by allowing dynamic addition of responsibilities to objects.
// In this case, we can add milk and sugar to a basic coffee without modifying the original Coffee class.
// The Decorator pattern is useful for adhering to the Open/Closed Principle, allowing us to extend functionality without altering existing code.
// Practical applications of the Decorator pattern include adding features to GUI components, enhancing logging functionality, or modifying data
// streams in I/O operations.
// The Decorator pattern is a structural design pattern that allows behavior to be added to individual objects, either statically or dynamically,
// without affecting the behavior of other objects from the same class.
// In this example, we have a Coffee interface that defines the basic structure for coffee objects. The BasicCoffee class implements this interface
// and provides a simple coffee implementation.
// The CoffeeDecorator abstract class implements the Coffee interface and serves as a base for concrete decorators.

// Scenarios in which the Decorator pattern is useful include:
// 1. When you want to add responsibilities to individual objects dynamically and transparently, without affecting other objects.
// 2. When you want to avoid subclassing for extending functionality, as it can lead to an explosion of subclasses.
// 3. When you want to combine multiple behaviors in a flexible and reusable way, allowing for a more modular design.
// 4. When you want to adhere to the Open/Closed Principle, allowing for easy extension of functionality without modifying existing code.
// 5. When you want to create a flexible and maintainable design that can adapt to changing requirements over time

// In summary, the Decorator Design Pattern is a powerful tool for enhancing the functionality of objects in a flexible and reusable manner,
// making it an essential pattern in software design.
// Enterprise applications often use the Decorator pattern to add features to components, such as adding security, logging, or caching capabilities
// to existing classes without modifying their code.
// The Decorator pattern is also commonly used in GUI frameworks to add visual enhancements to components, such as borders, scrollbars, or shadows,
// without altering the underlying component classes.

