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
