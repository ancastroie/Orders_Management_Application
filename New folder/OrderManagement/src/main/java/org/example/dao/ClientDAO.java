package org.example.dao;

import com.mysql.cj.protocol.Resultset;
import org.example.connection.ConnectionFactory;
import org.example.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The ClientDAO class is a DAO (Data Access Object) that provides specific functionality for working with the "client" table.
 * It extends the AbstractDAO class and inherits common database operations.
 */
public class ClientDAO extends AbstractDAO<Client>{

    private Connection connection;
    private Statement statement;

    /**
     * Constructs a ClientDAO object.
     *
     * @param connection The database connection to be used.
     */
    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves the maximum ID from the "client" table.
     *
     * @return The maximum ID value.
     * @throws SQLException If an SQL exception occurs.
     */
    public int getId() throws SQLException {
        int id=0;
        ResultSet result;
        String query="select max(id) from client";
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
     * Adds a new client to the "client" table.
     *
     * @param id    The ID of the client.
     * @param name  The name of the client.
     * @param age   The age of the client.
     * @param email The email of the client.
     * @throws SQLException           If an SQL exception occurs.
     * @throws IllegalAccessException If access to a field is denied.
     */
    public void addClient(int id,String name,int age,String email) throws SQLException, IllegalAccessException {
        Client client=new Client(id,name,age,email);
        insert(client);
    }
    /**
     * Deletes a client from the "client" table based on the ID.
     *
     * @param id The ID of the client to be deleted.
     * @throws IllegalAccessException If access to a field is denied.
     */
    public void deleteClient(int id) throws IllegalAccessException {

        //client=findById(id);
        //System.out.println(client.getName());
        String query="select * from client where id = ?";
        ResultSet result=null;
        try{Connection connection= ConnectionFactory.getConnection();
            PreparedStatement statement=null;
            statement=connection.prepareStatement(query);
            statement.setInt(1,id);
            result=statement.executeQuery();
            if( result.next()) {
                Client client = new Client(result.getInt("id"), result.getString("name"), result.getInt("age"), result.getString("email"));
                System.out.println(client.getName());
                delete(client);
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            ConnectionFactory.close(result);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

    }
    /**
     * Edits a client in the "client" table based on the ID.
     *
     * @param id    The ID of the client to be edited.
     * @param name  The new name of the client.
     * @param age   The new age of the client.
     * @param email The new email of the client.
     */

    public void editClient(int id,String name,String age,String email){
        String query="select * from client where id = ?";
        ResultSet result=null;
        try{Connection connection= ConnectionFactory.getConnection();
            PreparedStatement statement=null;
            statement=connection.prepareStatement(query);
            statement.setInt(1,id);
            result=statement.executeQuery();
            if( result.next()) {
                Client client = new Client(result.getInt("id"), result.getString("name"), result.getInt("age"), result.getString("email"));
                if(!name.isEmpty())client.setName(name);
                if(!age.isEmpty())client.setAge(Integer.valueOf(age));
                if(!email.isEmpty())client.setEmail(email);
                // System.out.println(client.getName()+client.getAge()+client.getEmail());
                update(client);
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            ConnectionFactory.close(result);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public ArrayList<Client> getAll() {
        ArrayList<Client> clients = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from client");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Client client = new Client(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("age"),resultSet.getString("email"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

}