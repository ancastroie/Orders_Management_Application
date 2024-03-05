package org.example.view;

import org.example.connection.ConnectionFactory;
import org.example.dao.AbstractDAO;
import org.example.dao.ClientDAO;
import org.example.dao.ProductDAO;
import org.example.model.Client;
import org.example.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ProductTableView extends JFrame {

    public ProductTableView() throws SQLException {
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Product Table View");

        Connection connection= ConnectionFactory.getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from product");

        // Retrieve client data from the database
        ProductDAO productDAO = new ProductDAO(connection);
        ArrayList<Product> products = productDAO.getAll();

        String[] columns=productDAO.getHeader(products.get(0));

        Object[][] rows=new Object[products.size()][];
        for (int i=0;i<products.size();i++){
            rows[i]=productDAO.getRowData(products.get(i));
        }
        Object[][] trimmedData = new Object[rows.length][columns.length];
        System.arraycopy(rows, 0, trimmedData, 0, rows.length);

        JTable table = new JTable(trimmedData,columns);

        JScrollPane scrollPane = new JScrollPane(table);
        JFrame frame = new JFrame("Database");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane);
        frame.setBounds(100,100,600, 400);
        frame.setVisible(true);

    }

}