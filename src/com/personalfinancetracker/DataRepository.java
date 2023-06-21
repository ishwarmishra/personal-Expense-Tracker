package com.personalfinancetracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataRepository {

    private Connection conn;

    // Other methods of the DataRepository class...
    private Connection con = null;

    public Connection returnConnection() {
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        String URL = "jdbc:mysql://localhost:3306/personalFinanceTracker";
        String USERNAME = "root";
        String PASSWORD = "new_password";

       
        try {
            con = DriverManager.getConnection(
                    URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DataRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
    }
}
