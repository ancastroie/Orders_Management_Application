package org.example.view;

import org.example.connection.ConnectionFactory;
import org.example.dao.AbstractDAO;
import org.example.dao.ClientDAO;
import org.example.model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The ClientTableView class represents a JFrame that displays a table view of client data from a database.
 */
public class ClientTableView extends JFrame {

    /**
     * Constructs a ClientTableView object and initializes the table view.
     *
     * @throws SQLException if a database access error occurs
     */
    public ClientTableView() throws SQLException {
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client Table View");

        Connection connection= ConnectionFactory.getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from client");

        // Retrieve client data from the database
        ClientDAO clientDAO = new ClientDAO(connection);
        ArrayList<Client> clients = clientDAO.getAll();

        String[] columns=clientDAO.getHeader(clients.get(0));

        Object[][] rows=new Object[clients.size()][];
        for (int i=0;i<clients.size();i++){
            rows[i]=clientDAO.getRowData(clients.get(i));
        }
        Object[][] trimmedData = new Object[rows.length][columns.length];
        System.arraycopy(rows, 0, trimmedData, 0, rows.length);

        JTable table = new JTable(trimmedData,columns);

        JScrollPane scrollPane = new JScrollPane(table);
        JFrame frame = new JFrame("Database Table Example");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane);
        frame.setBounds(100,100,600, 400);
        frame.setVisible(true);

    }


}