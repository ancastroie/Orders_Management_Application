package org.example.view;

import org.example.connection.ConnectionFactory;
import org.example.dao.AbstractDAO;
import org.example.dao.ClientDAO;
import org.example.dao.OrdersDAO;
import org.example.dao.ProductDAO;
import org.example.model.Client;
import org.example.model.Orders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class OrderGUI extends JFrame implements ActionListener{

    Choice cprod,cclient;
    JTextField quantity;
    JButton send,back;
    OrderGUI() throws SQLException {
        setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel l1=new JLabel("Choose a product");
        l1.setBounds(40,70,150,40);
        add(l1);
        cprod=new Choice();
        cprod.setBounds(40,120,150,40);

        JLabel l2=new JLabel("Select a client");
        l2.setBounds(40,190,150,40);
        add(l2);
        cclient=new Choice();
        cclient.setBounds(40,240,150,40);

        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product");

            while (resultSet.next()) {
                cprod.add(resultSet.getString("name"));

            }
            resultSet=statement.executeQuery("select * from client");

            while(resultSet.next()){
                cclient.add(resultSet.getString("name"));
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        add(cprod);
        add(cclient);

        JLabel l3=new JLabel("Insert quantity ");
        l3.setBounds(40,310,150,40);
        add(l3);
        quantity=new JTextField();
        quantity.setBounds(40,360,150,40);
        add(quantity);

        send=new JButton("SEND");
        send.setBackground(Color.BLACK);
        send.setForeground(Color.WHITE);
        send.setBounds(100,450,150,30);
        send.addActionListener(this);
        add(send);

        back=new JButton("BACK");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(300,450,150,30);
        back.addActionListener(this);
        add(back);

        JLabel l=new JLabel("Make an order ");
        l.setBounds(300,20,150,50);
        add(l);

        getContentPane().setBackground(Color.WHITE);
        this.setBounds(100,100,800,600);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("BACK")){
            this.setVisible(false);
            new Dashboard();
        }
        if(e.getActionCommand().equals("SEND")){
            String client=cclient.getSelectedItem();
            String product=cprod.getSelectedItem();
            String quantityNr=quantity.getText();
            int intQuantity;
            int id,productQuantity=0;
            try{
                Connection connection= ConnectionFactory.getConnection();
                OrdersDAO ordersDAO=new OrdersDAO(connection);
                id=ordersDAO.getId();
                System.out.println(id);
                intQuantity=Integer.valueOf(quantityNr);
                StringBuilder query=new StringBuilder();
                int price=0;
                query.append("select * from product where name='").append(product).append("'");
                Statement statement=connection.createStatement();
                System.out.println(query);
                ResultSet result= statement.executeQuery(query.toString());
                while(result.next()){
                    price=result.getInt("price");
                    productQuantity=result.getInt("quantity");
                }
                if(productQuantity>=intQuantity) {
                    price = price * intQuantity;
                    Orders orders = new Orders(id, client, product, intQuantity, price);
                    ordersDAO.addNewOrder(id, client, product, intQuantity, price);
                }else {
                    JOptionPane.showMessageDialog(null, "There are not enough products for the order!");
                }

            }catch (Exception ae){
                ae.printStackTrace();
            }
        }
    }
}
