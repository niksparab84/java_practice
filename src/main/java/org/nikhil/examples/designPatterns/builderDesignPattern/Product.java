package org.nikhil.examples.designPatterns.builderDesignPattern;

// Product class
public class Product {
    private final String name;
    private final double price;
    private final String description;

    private Product(Builder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    // Static nested Builder class
    public static class Builder {
        private String name;
        private double price;
        private String description;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    public static void main(String[] args) {
        Product product = new Product.Builder()
                .setName("Laptop")
                .setPrice(1200.00)
                .build();

        System.out.println("Product Name: " + product.getName());
        System.out.println("Product Price: " + product.getPrice());
        System.out.println("Product Description: " + product.getDescription());
    }

    //where should we use this builder pattern?
    // The builder pattern is useful when you have a class with many parameters, especially optional ones.
    // It allows you to create instances of the class in a readable and maintainable way.
    // It is commonly used in scenarios like constructing complex objects, such as in GUI frameworks,
    // configuration objects, or when dealing with immutable objects.
    // The builder pattern is particularly useful in scenarios where:
    // 1. The class has many parameters, especially optional ones.
    // 2. You want to create immutable objects.
    // 3. You want to improve code readability and maintainability.
    // 4. You want to avoid telescoping constructor anti-pattern.
    // 5. You want to provide a fluent interface for object creation.
    // 6. You want to separate the construction of an object from its representation.
}