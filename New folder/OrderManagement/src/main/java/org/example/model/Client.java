package org.example.model;
/**
 * The Client class represents a client entity with properties such as ID, name, age, and email.
 */
public class Client {
    int id;
    String name;
    int age;
    String email;

    /**
     * Constructs a Client object with the specified ID, name, age, and email.
     *
     * @param id    The ID of the client.
     * @param name  The name of the client.
     * @param age   The age of the client.
     * @param email The email of the client.
     */
    public Client(int id,String name, int age, String email) {
        this.id = id;
        this.name=name;
        this.age=age;
        this.email=email;
    }

    /**
     * Returns the email of the client.
     *
     * @return The email of the client.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the client.
     *
     * @param email The email to set for the client.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the name of the client.
     *
     * @return The name of the client.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the client.
     *
     * @return The age of the client.
     */
    public int getAge() {
        return age;
    }
    /**
     * Sets the age of the client.
     *
     * @param age The age to set for the client.
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * Sets the name of the client.
     *
     * @param name The name to set for the client.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns the ID of the client.
     *
     * @return The ID of the client.
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the ID of the client.
     *
     * @param id The ID to set for the client.
     */
    public void setId(int id) {
        this.id = id;
    }
}