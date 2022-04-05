/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Refka
 */
public class MaConnection {
    
    public String url ="jdbc:mysql://127.0.0.1:3306/goodgames";
    public String login="root";
    public String pwd ="";
    public Connection cnx;
    public static MaConnection ct;

    private MaConnection() {
        try {
           cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Cnx etablie");
        } catch (SQLException ex) {
            System.out.println("Problème de cnx");
            System.out.println(ex.getMessage());
        }
    
    }
    public Connection getConnection(){
        return cnx;
    }
    public static MaConnection getInstance(){
        if(ct == null)
            ct = new MaConnection();
        return ct;
        
    } 
    public Connection getCnx() {
        return cnx;
    }
    
    
}
