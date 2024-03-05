package org.example.view;

import org.example.connection.ConnectionFactory;
import org.example.dao.AbstractDAO;
import org.example.dao.ClientDAO;
import org.example.model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Dashboard extends JFrame implements ActionListener {

    /**
     * The Dashboard class represents the main dashboard of the application.
     * It provides buttons to navigate to different operations related to clients, products, and orders.
     */

    JButton client,product,order;
    /**
     * Constructs a Dashboard object and initializes the main dashboard.
     */
    public Dashboard() {
        setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        client=new JButton("Client operations");
        client.setBackground(Color.BLACK);
        client.setForeground(Color.WHITE);
        client.setBounds(70,100,200,40);
        client.addActionListener(this);
        add(client);

        product=new JButton("Product operations");
        product.setBackground(Color.BLACK);
        product.setForeground(Color.WHITE);
        product.setBounds(70,170,200,40);
        product.addActionListener(this);
        add(product);

        order=new JButton("Make an order");
        order.setBackground(Color.BLACK);
        order.setForeground(Color.WHITE);
        order.setBounds(70,240,200,40);
        order.addActionListener(this);
        add(order);

        getContentPane().setBackground(Color.WHITE);
        this.setBounds(200,200,500,500);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("Client operations")){
            ClientGui client=new ClientGui();
            this.setVisible(false);
        }
        if(e.getActionCommand().equals("Product operations")){
            ProductGui product=new ProductGui();
            this.setVisible(false);
        }
        if(e.getActionCommand().equals("Make an order")){

            try {
                OrderGUI orderGUI=new OrderGUI();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.setVisible(false);
        }
    }
}
