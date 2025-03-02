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
