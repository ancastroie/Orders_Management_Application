package org.example.model;
/**
 * The Product class represents a product entity with properties such as ID, name, quantity, and price.
 */
public class Product {
    int id;
    String name;
    int quantity;
    int price;
    /**
     * Constructs a Product object with the specified ID, name, quantity, and price.
     *
     * @param id       The ID of the product.
     * @param name     The name of the product.
     * @param quantity The quantity of the product.
     * @param price    The price of the product.
     */
    public Product(int id,String name,int quantity,int price) {
        this.id = id;
        this.name=name;
        this.quantity=quantity;
        this.price=price;
    }

    /**
     * Returns the price of the product.
     *
     * @return The price of the product.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The price to set for the product.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Returns the quantity of the product.
     *
     * @return The quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product.
     *
     * @param quantity The quantity to set for the product.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the name of the product.
     *
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The name to set for the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the ID of the product.
     *
     * @return The ID of the product.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id The ID to set for the product.
     */
    public void setId(int id) {
        this.id = id;
    }
}