package org.example.dao;

import com.mysql.cj.protocol.Resultset;
import org.example.connection.ConnectionFactory;
import org.example.model.Client;
import org.example.model.Orders;
import org.example.model.Product;
import org.example.view.OrderGUI;
import org.example.view.ProductGui;

import java.sql.*;
/**
 * The OrdersDAO class is a DAO (Data Access Object) that provides specific functionality for working with the "orders" table.
 * It extends the AbstractDAO class and inherits common database operations.
 */
public class OrdersDAO extends AbstractDAO<Orders>{

    private Connection connection;
    private Statement statement;

    /**
     * Constructs an OrdersDAO object.
     *
     * @param connection The database connection to be used.
     */
    public OrdersDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves the maximum ID from the "orders" table.
     *
     * @return The maximum ID value.
     * @throws SQLException If an SQL exception occurs.
     */
    public int getId() throws SQLException {
        int id=0;
        ResultSet result;
        String query="select max(id) from orders";
        PreparedStatement statement=null;
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            if (result.next())
                id = result.getInt(1);

            id++;
        }finally{
            if(statement!=null)
                statement.close();
        }
        return id;
    }

    /**
     * Adds a new order to the "orders" table.
     * Updates the quantity and price of the product in the "product" table.
     *
     * @param id       The ID of the order.
     * @param client   The client's name.
     * @param product  The product's name.
     * @param quantity The quantity of the product.
     * @param price    The price of the product.
     * @throws SQLException           If an SQL exception occurs.
     * @throws IllegalAccessException If access to a field is denied.
     */
    public void addNewOrder(int id,String client,String product,int quantity,int price) throws SQLException, IllegalAccessException {
        Orders orders=new Orders(id,client,product,quantity,price);
        insert(orders);

        String query="select * from product where name = ?";
        ResultSet result=null;
        try{Connection connection= ConnectionFactory.getConnection();
            PreparedStatement statement=null;
            statement=connection.prepareStatement(query);
            statement.setString(1,product);
            result=statement.executeQuery();

            if( result.next()) {
                ProductDAO pr=new ProductDAO(connection);
                pr.editProduct(result.getInt("id"),product,String.valueOf(result.getInt("quantity")-quantity),String.valueOf(result.getInt("price")));
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            ConnectionFactory.close(result);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }


}