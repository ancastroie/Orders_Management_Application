package org.example.view;

import org.example.connection.ConnectionFactory;
import org.example.dao.AbstractDAO;
import org.example.dao.ClientDAO;
import org.example.dao.ProductDAO;
import org.example.model.Client;
import org.example.model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductGui extends JFrame implements ActionListener {
    JTextField tfname,tfquantity,tfprice,tfId,tfIdEdit,tfnameEdit,tfquantityEdit,tfpriceEdit;
    JButton submit,delete,edit,showData,back;
    public ProductGui(){
        setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JPanel pAdd=new JPanel();
        JLabel l=new JLabel("Add new product");
        l.setBounds(100,10,100,20);
        pAdd.setBounds(30,20,350,250);
        pAdd.setBorder(BorderFactory.createLineBorder(Color.black));
        pAdd.setLayout(null);
        pAdd.add(l);
        this.add(pAdd);

        JLabel lblname=new JLabel("NAME");
        lblname.setBounds(30,50,120,30);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,17));
        pAdd.add(lblname);

        tfname=new JTextField();
        tfname.setBounds(120,50,150,30);
        pAdd.add(tfname);

        JLabel lblage=new JLabel("QUANTITY");
        lblage.setBounds(30,100,120,30);
        lblage.setFont(new Font("Tahoma",Font.PLAIN,17));
        pAdd.add(lblage);

        tfquantity=new JTextField();
        tfquantity.setBounds(120,100,150,30);
        pAdd.add(tfquantity);

        JLabel lblemail=new JLabel("PRICE");
        lblemail.setBounds(30,150,120,30);
        lblemail.setFont(new Font("Tahoma",Font.PLAIN,17));
        pAdd.add(lblemail);

        tfprice=new JTextField();
        tfprice.setBounds(120,150,150,30);
        pAdd.add(tfprice);

        submit=new JButton("SUBMIT");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(100,200,150,30);
        submit.addActionListener(this);
        pAdd.add(submit);
/////////////////
        JPanel pDel=new JPanel();
        JLabel l2=new JLabel("Delete client");
        l2.setBounds(100,10,100,20);
        pDel.setBounds(30,290,350,150);
        pDel.setBorder(BorderFactory.createLineBorder(Color.black));
        pDel.setLayout(null);
        pDel.add(l2);
        this.add(pDel);

        JLabel lId=new JLabel("PRODUCT ID");
        lId.setBounds(30,50,120,30);
        lId.setFont(new Font("Tahoma",Font.PLAIN,17));
        pDel.add(lId);

        tfId=new JTextField();
        tfId.setBounds(130,50,150,30);
        pDel.add(tfId);

        delete=new JButton("DELETE");
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.setBounds(100,100,150,30);
        delete.addActionListener(this);
        pDel.add(delete);
        ///////////////////

        JPanel pEdit=new JPanel();
        JLabel l3=new JLabel("Edit product  (enter the id of the client you want to modify)");
        l3.setBounds(10,10,350,20);
        pEdit.setBounds(400,20,350,300);
        pEdit.setBorder(BorderFactory.createLineBorder(Color.black));
        pEdit.setLayout(null);
        pEdit.add(l3);
        this.add(pEdit);

        JLabel lIdEdit=new JLabel("PRODUCT ID");
        lIdEdit.setBounds(30,50,120,30);
        lIdEdit.setFont(new Font("Tahoma",Font.PLAIN,17));
        pEdit.add(lIdEdit);

        tfIdEdit=new JTextField();
        tfIdEdit.setBounds(130,50,150,30);
        pEdit.add(tfIdEdit);

        JLabel lblnameEdit=new JLabel("NAME");
        lblnameEdit.setBounds(30,100,120,30);
        lblnameEdit.setFont(new Font("Tahoma",Font.PLAIN,17));
        pEdit.add(lblnameEdit);

        tfnameEdit=new JTextField();
        tfnameEdit.setBounds(130,100,150,30);
        pEdit.add(tfnameEdit);

        JLabel lblageEdit=new JLabel("QUANTITY");
        lblageEdit.setBounds(30,150,120,30);
        lblageEdit.setFont(new Font("Tahoma",Font.PLAIN,17));
        pEdit.add(lblageEdit);

        tfquantityEdit=new JTextField();
        tfquantityEdit.setBounds(130,150,150,30);
        pEdit.add(tfquantityEdit);

        JLabel lblemailEdit=new JLabel("PRICE");
        lblemailEdit.setBounds(30,200,120,30);
        lblemailEdit.setFont(new Font("Tahoma",Font.PLAIN,17));
        pEdit.add(lblemailEdit);

        tfpriceEdit=new JTextField();
        tfpriceEdit.setBounds(130,200,150,30);
        pEdit.add(tfpriceEdit);


        edit=new JButton("EDIT");
        edit.setBackground(Color.BLACK);
        edit.setForeground(Color.WHITE);
        edit.setBounds(100,250,150,30);
        edit.addActionListener(this);
        pEdit.add(edit);
////////////////

        back=new JButton("BACK");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(500,500,200,40);
        back.addActionListener(this);
        add(back);

        showData=new JButton("SHOW TABLE");
        showData.setBackground(Color.BLACK);
        showData.setForeground(Color.WHITE);
        showData.setBounds(450,350,200,40);
        showData.addActionListener(this);
        this.add(showData);

        getContentPane().setBackground(Color.WHITE);
        this.setBounds(100,100,800,600);

        setVisible(true);


    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getActionCommand().equals("SUBMIT")){
            String name=tfname.getText();
            String quantity =tfquantity.getText();
            String price=tfprice.getText();
            int id;

            if(name.equals("")){
                JOptionPane.showMessageDialog(null,"Name should not be empty");
                return;
            }
            if(quantity.equals("")){
                JOptionPane.showMessageDialog(null,"Quantity should not be empty");
                return;
            }

            if(price.equals("")){
                JOptionPane.showMessageDialog(null,"Price should not be empty");
                return;
            }

            try{
                Connection connection= ConnectionFactory.getConnection();
                ProductDAO productDAO=new ProductDAO(connection);
                id=productDAO.getId();
               Product product=new Product(id,name,Integer.valueOf(quantity),Integer.valueOf(price));
                productDAO.addNewProduct(id,name,Integer.valueOf(quantity),Integer.valueOf(price));

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(ae.getActionCommand().equals("DELETE")){
            String id=tfId.getText();
            if(id.equals("")){
                JOptionPane.showMessageDialog(null,"Id should not be empty");
                return;
            }
            int idNr=Integer.valueOf(id);
            try{
                Connection connection= ConnectionFactory.getConnection();
                ProductDAO productDAO=new ProductDAO(connection);
                System.out.println(idNr);

                productDAO.deleteProduct(idNr);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(ae.getActionCommand().equals("EDIT")){
            String id=tfIdEdit.getText();
            String name=tfnameEdit.getText();
            String quantity=tfquantityEdit.getText();
            String price=tfpriceEdit.getText();
            if(id.equals("")){
                JOptionPane.showMessageDialog(null,"Id should not be empty");
                return;
            }
            int idNr=Integer.valueOf(id);
            try{
                Connection connection= ConnectionFactory.getConnection();
                ProductDAO productDAO=new ProductDAO(connection);
                productDAO.editProduct(idNr,name,quantity,price);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(ae.getActionCommand().equals("SHOW TABLE")){
            try {
                ProductTableView productTableView=new ProductTableView();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(ae.getActionCommand().equals("BACK")){
            Dashboard dash=new Dashboard();
            this.setVisible(false);
        }

    }
}