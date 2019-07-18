/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class DBConnection {              
    
    
     private static Connection conn;
    
    public static Connection getConnection(){return conn;}
  // public void closeConnection(){} 
    
    static
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
             conn=DriverManager.getConnection("jdbc:Oracle:thin:@//DESKTOP-84TVMJT:1521/XE", "rohan", "jain");
     //        JOptionPane.showMessageDialog(null,"Connected Successfully","Success!",JOptionPane.INFORMATION_MESSAGE);      
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null,"ERROR LOADIING DRIVER CLASS"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        catch(SQLException ex)
        {
         JOptionPane.showMessageDialog(null,"SQLError"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
        }
    }
    
}
