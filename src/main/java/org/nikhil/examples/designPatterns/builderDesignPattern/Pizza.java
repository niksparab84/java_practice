package org.nikhil.examples.designPatterns.builderDesignPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pizza {
    private final String size;
    private final String crust;
    private final String sauce;
    private final List<String> topping;

    public Pizza(PizzaBuilder pizzaBuilder) {
        this.size = pizzaBuilder.size;
        this.crust = pizzaBuilder.crust;
        this.sauce = pizzaBuilder.sauce;
        this.topping = pizzaBuilder.topping;
    }

    public String getSize() {
        return size;
    }

    public String getCrust() {
        return crust;
    }

    public String getSauce() {
        return sauce;
    }

    public List<String> getTopping() {
        return topping;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "size='" + size + '\'' +
                ", crust='" + crust + '\'' +
                ", sauce='" + sauce + '\'' +
                ", topping=" + topping +
                '}';
    }

    // Static nested Builder class
    public static class PizzaBuilder {
        private String size;
        private String crust;
        private String sauce;
        private List<String> topping;

        public PizzaBuilder setSize(String size) {
            this.size = size;
            return this;
        }

        public PizzaBuilder setCrust(String crust) {
            this.crust = crust;
            return this;
        }

        public PizzaBuilder setSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public PizzaBuilder setTopping(List<String> topping) {
            this.topping = topping;
            return this;
        }

        public Pizza buildPizza() {
            return new Pizza(this);
        }
    }

    public static void main(String[] args) {
        Pizza pizza = new Pizza.PizzaBuilder()
                .setSize("Medium")
                .setCrust("Thin")
                .setSauce("Tomato")
                .setTopping(Arrays.asList("Cheese", "Mushrooms", "Olives"))
                .buildPizza();

        System.out.println(pizza);
    }
}

// The Builder Design Pattern is a creational design pattern that provides a step-by-step approach to constructing complex objects.
// It separates the construction process from the object’s representation, enabling the same method to create different variations of an object.
// Encapsulates object construction logic in a separate Builder class, enabling flexible and controlled creation.
// Supports creating different variations of a product using the same construction process.
// In this example, the `Pizza` class is constructed using a nested static `PizzaBuilder` class, which provides methods to set various properties of the pizza and a method to build the final `Pizza` object.
// The `PizzaBuilder` class allows for a fluent interface, enabling method chaining to set the properties of the pizza. The `buildPizza()` method constructs and returns the final `Pizza` object. This pattern is particularly useful when an object has many optional parameters or when the construction process is complex.
//
