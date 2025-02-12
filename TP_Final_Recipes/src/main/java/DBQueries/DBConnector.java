/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmed
 * This is the DB Connector for the database. It respects the Singleton Design Pattern
 */
public class DBConnector {
    
    private final String DB_HOST = "mysql-ahmedfguezzane.alwaysdata.net:3306";
    private final String DB_NAME = "ahmedfguezzane_grubgenius";
    private final String DB_USER = "389938_soraya";
    private final String DB_PASSWORD = "adminsoraya";
    private final String DB_URL = "jdbc:mariadb://" + DB_HOST + "/" + DB_NAME;
    
    private Connection conn = null;
    
    private static DBConnector instance = null;
    
    public static DBConnector getInstance(){
        if(instance == null)
        {
            instance = new DBConnector();
        }
        return instance;
    }
    
    private DBConnector(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            this.conn = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PASSWORD);
        }
        catch(SQLException e) {throw new RuntimeException(e);} catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection()
    {
        return this.conn;
    }
}
