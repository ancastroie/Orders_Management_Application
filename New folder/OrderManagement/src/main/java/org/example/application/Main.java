package org.example.application;

import org.example.view.ClientGui;
import org.example.view.ClientTableView;
import org.example.view.Dashboard;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");
        //ClientGui clientGui=new ClientGui();
        Dashboard dash=new Dashboard();
      //  ClientTableView cl=new ClientTableView();
    }
}