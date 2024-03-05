package org.example.model;
/**
 * The Orders class represents an order entity with properties such as ID, client, product, quantity, and price.
 */
public class Orders {
    int id;
    String client;
    String product;
    int quantity;
    int price;
    /**
     * Constructs an Orders object with the specified ID, client, product, quantity, and price.
     *
     * @param id       The ID of the order.
     * @param client   The client associated with the order.
     * @param product  The product associated with the order.
     * @param quantity The quantity of the product in the order.
     * @param price    The price of the order.
     */
    public Orders(int id,String client,String product,int quantity,int price) {
        this.id = id;
        this.client=client;
        this.product=product;
        this.quantity=quantity;
        this.price=price;
    }

    /**
     * Returns the price of the order.
     *
     * @return The price of the order.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the order.
     *
     * @param price The price to set for the order.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Returns the quantity of the product in the order.
     *
     * @return The quantity of the product in the order.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in the order.
     *
     * @param quantity The quantity to set for the order.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the product associated with the order.
     *
     * @return The product associated with the order.
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the product associated with the order.
     *
     * @param product The product to set for the order.
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * Returns the client associated with the order.
     *
     * @return The client associated with the order.
     */
    public String getClient() {
        return client;
    }

    /**
     * Sets the client associated with the order.
     *
     * @param client The client to set for the order.
     */
    public void setClient(String client) {
        this.client = client;
    }

    /**
     * Returns the ID of the order.
     *
     * @return The ID of the order.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the order.
     *
     * @param id The ID to set for the order.
     */
    public void setId(int id) {
        this.id = id;
    }
}
