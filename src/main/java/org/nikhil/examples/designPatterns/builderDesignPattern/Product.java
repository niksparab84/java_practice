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
}