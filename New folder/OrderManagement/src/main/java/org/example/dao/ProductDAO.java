package org.example.dao;

import com.mysql.cj.protocol.Resultset;
import org.example.connection.ConnectionFactory;
import org.example.model.Client;
import org.example.model.Product;

import java.sql.*;
import java.util.ArrayList;

/**
 * The ProductDAO class is a DAO (Data Access Object) that provides specific functionality for working with the "product" table.
 * It extends the AbstractDAO class and inherits common database operations.
 */

public class ProductDAO extends AbstractDAO<Product>{

    private Connection connection;
    private Statement statement;
    /**
     * Constructs a ProductDAO object.
     *
     * @param connection The database connection to be used.
     */

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves the maximum ID from the "product" table.
     *
     * @return The maximum ID value.
     * @throws SQLException If an SQL exception occurs.
     */
    public int getId() throws SQLException {
        int id=0;
        ResultSet result;
        String query="select max(id) from product";
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
     * Adds a new product to the "product" table.
     *
     * @param id       The ID of the product.
     * @param name     The name of the product.
     * @param quantity The quantity of the product.
     * @param price    The price of the product.
     * @throws SQLException           If an SQL exception occurs.
     * @throws IllegalAccessException If access to a field is denied.
     */
    public void addNewProduct(int id,String name,int quantity,int price) throws SQLException, IllegalAccessException {
        Product product=new Product(id,name,quantity,price);
        insert(product);
    }
    /**
     * Deletes a product from the "product" table.
     *
     * @param id The ID of the product to delete.
     * @throws IllegalAccessException If access to a field is denied.
     */
    public void deleteProduct(int id) throws IllegalAccessException {

        //client=findById(id);
        //System.out.println(client.getName());
        String query="select * from product where id = ?";
        ResultSet result=null;
        try{Connection connection= ConnectionFactory.getConnection();
            PreparedStatement statement=null;
            statement=connection.prepareStatement(query);
            statement.setInt(1,id);
            result=statement.executeQuery();
            if( result.next()) {
                Product product = new Product(result.getInt("id"), result.getString("name"), result.getInt("quantity"), result.getInt("price"));
                System.out.println(product.getName());
                delete(product);
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            ConnectionFactory.close(result);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

    }

    /**
     * Edits a product in the "product" table.
     *
     * @param id       The ID of the product to edit.
     * @param name     The new name of the product.
     * @param quantity The new quantity of the product.
     * @param price    The new price of the product.
     */
    public void editProduct(int id,String name,String quantity,String price){
        String query="select * from product where id = ?";
        ResultSet result=null;
        try{Connection connection= ConnectionFactory.getConnection();
            PreparedStatement statement=null;
            statement=connection.prepareStatement(query);
            statement.setInt(1,id);
            result=statement.executeQuery();
            if( result.next()) {
                Product product = new Product(result.getInt("id"), result.getString("name"), result.getInt("quantity"), result.getInt("price"));
                if(!name.isEmpty())product.setName(name);
                if(!quantity.isEmpty())product.setQuantity(Integer.valueOf(quantity));
                if(!price.isEmpty())product.setPrice(Integer.valueOf(price));
                update(product);

            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            ConnectionFactory.close(result);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public ArrayList<Product> getAll() {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from product");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("quantity"),resultSet.getInt("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}