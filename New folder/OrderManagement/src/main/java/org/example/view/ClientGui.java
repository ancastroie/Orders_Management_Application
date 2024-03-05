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
/**
 * The ClientGui class represents a graphical user interface (GUI) for managing client information.
 * It provides functionality for adding, deleting, editing, and displaying client data.
 * The GUI components include text fields, buttons, and labels.
 */
public class ClientGui extends JFrame implements ActionListener {
    JTextField tfname,tfemail,tfage,tfId,tfIdEdit,tfnameEdit,tfageEdit,tfemailEdit;
    JButton submit,delete,edit,showData,back;
    /**
     * Text field for entering the client's name when adding a new client.
     * Text field for entering the client's email when adding a new client
     * Text field for entering the client's age when adding a new client.
     * Text field for entering the client's ID when deleting or editing a client.
     * Text field for entering the client's ID when editing a client.
     * Text field for entering the client's name when editing a client.
     * Text field for entering the client's age when editing a client.
     * Text field for entering the client's email when editing a client.
     * Button for submitting the new client information.
     * Button for deleting a client.
     * Button for editing a client.
     * Button for displaying the client table.
     * Button for navigating back to the main dashboard.
     */
    /**
     * Constructs a new ClientGui object.
     * Initializes and sets up the graphical user interface.
     */
    public ClientGui(){
        setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JPanel pAdd=new JPanel();
        JLabel l=new JLabel("Add new client");
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
        tfname.setBounds(100,50,150,30);
        pAdd.add(tfname);

        JLabel lblage=new JLabel("AGE");
        lblage.setBounds(30,100,120,30);
        lblage.setFont(new Font("Tahoma",Font.PLAIN,17));
        pAdd.add(lblage);

        tfage=new JTextField();
        tfage.setBounds(100,100,150,30);
        pAdd.add(tfage);

        JLabel lblemail=new JLabel("EMAIL");
        lblemail.setBounds(30,150,120,30);
        lblemail.setFont(new Font("Tahoma",Font.PLAIN,17));
        pAdd.add(lblemail);

        tfemail=new JTextField();
        tfemail.setBounds(100,150,150,30);
        pAdd.add(tfemail);

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

        JLabel lId=new JLabel("Client id");
        lId.setBounds(30,50,120,30);
        lId.setFont(new Font("Tahoma",Font.PLAIN,17));
        pDel.add(lId);

        tfId=new JTextField();
        tfId.setBounds(100,50,150,30);
        pDel.add(tfId);

        delete=new JButton("DELETE");
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.setBounds(100,100,150,30);
        delete.addActionListener(this);
        pDel.add(delete);
        ///////////////////

        JPanel pEdit=new JPanel();
        JLabel l3=new JLabel("Edit client  (enter the id of the client you want to modify)");
        l3.setBounds(10,10,350,20);
        pEdit.setBounds(400,20,350,300);
        pEdit.setBorder(BorderFactory.createLineBorder(Color.black));
        pEdit.setLayout(null);
        pEdit.add(l3);
        this.add(pEdit);

        JLabel lIdEdit=new JLabel("Client id");
        lIdEdit.setBounds(30,50,120,30);
        lIdEdit.setFont(new Font("Tahoma",Font.PLAIN,17));
        pEdit.add(lIdEdit);

        tfIdEdit=new JTextField();
        tfIdEdit.setBounds(100,50,150,30);
        pEdit.add(tfIdEdit);

        JLabel lblnameEdit=new JLabel("NAME");
        lblnameEdit.setBounds(30,100,120,30);
        lblnameEdit.setFont(new Font("Tahoma",Font.PLAIN,17));
        pEdit.add(lblnameEdit);

        tfnameEdit=new JTextField();
        tfnameEdit.setBounds(100,100,150,30);
        pEdit.add(tfnameEdit);

        JLabel lblageEdit=new JLabel("AGE");
        lblageEdit.setBounds(30,150,120,30);
        lblageEdit.setFont(new Font("Tahoma",Font.PLAIN,17));
        pEdit.add(lblageEdit);

        tfageEdit=new JTextField();
        tfageEdit.setBounds(100,150,150,30);
        pEdit.add(tfageEdit);

        JLabel lblemailEdit=new JLabel("EMAIL");
        lblemailEdit.setBounds(30,200,120,30);
        lblemailEdit.setFont(new Font("Tahoma",Font.PLAIN,17));
        pEdit.add(lblemailEdit);

        tfemailEdit=new JTextField();
        tfemailEdit.setBounds(100,200,150,30);
        pEdit.add(tfemailEdit);


        edit=new JButton("EDIT");
        edit.setBackground(Color.BLACK);
        edit.setForeground(Color.WHITE);
        edit.setBounds(100,250,150,30);
        edit.addActionListener(this);
        pEdit.add(edit);
////////////////


        showData=new JButton("SHOW TABLE");
        showData.setBackground(Color.BLACK);
        showData.setForeground(Color.WHITE);
        showData.setBounds(450,350,200,40);
        showData.addActionListener(this);
        this.add(showData);


        back=new JButton("BACK");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(500,500,200,40);
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(Color.WHITE);
        this.setBounds(100,100,800,600);

        setVisible(true);


    }
    /**
     * Handles the action events triggered by the GUI components.
     * Performs appropriate actions based on the event source.
     *
     * @param ae the ActionEvent representing the user's action
     */
    public void actionPerformed(ActionEvent ae){

        if(ae.getActionCommand().equals("SUBMIT")){
            String name=tfname.getText();
            String age =tfage.getText();
            String email=tfemail.getText();
            int id;

            if(name.equals("")){
                JOptionPane.showMessageDialog(null,"Name should not be empty");
                return;
            }
            if(age.equals("")){
                JOptionPane.showMessageDialog(null,"Age should not be empty");
                return;
            }

            if(email.equals("")){
                JOptionPane.showMessageDialog(null,"Email should not be empty");
                return;
            }

            try{
                Connection connection= ConnectionFactory.getConnection();
                ClientDAO clientDAO=new ClientDAO(connection);
                id=clientDAO.getId();
                Client client=new Client(id,name,Integer.valueOf(age),email);
                clientDAO.addClient(id,name,Integer.valueOf(age),email);

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
                ClientDAO clientDAO=new ClientDAO(connection);
                System.out.println(idNr);

                clientDAO.deleteClient(idNr);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(ae.getActionCommand().equals("EDIT")){
            String id=tfIdEdit.getText();
            String name=tfnameEdit.getText();
            String age=tfageEdit.getText();
            String email=tfemailEdit.getText();
            if(id.equals("")){
                JOptionPane.showMessageDialog(null,"Id should not be empty");
                return;
            }
            int idNr=Integer.valueOf(id);
            try{
                Connection connection= ConnectionFactory.getConnection();
                ClientDAO clientDAO=new ClientDAO(connection);
                clientDAO.editClient(idNr,name,age,email);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(ae.getActionCommand().equals("SHOW TABLE")){
            try {
                ClientTableView clientTableView=new ClientTableView();
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