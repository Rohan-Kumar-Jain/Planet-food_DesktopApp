/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Product;
import planetfood.pojo.User;

/**
 *
 * @author Dell
 */
public class UserDao {
    
    public static String validateUser(User user)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="select user_name from users where user_id=? and password=? and user_type=?";
    PreparedStatement ps=conn.prepareStatement(qry);
    ps.setString(1,user.getUserId());
    ps.setString(2,user.getPassword());
    ps.setString(3,user.getUserType());
    ResultSet rs=ps.executeQuery();
    String userName=null;
    if(rs.next())
    {
        userName=rs.getString(1);//System.out.println(userName);
        return userName;
    }
    
     //   System.out.println("rohan");
    return userName;
    }
    
     public static boolean addUser(User u,String name,String empId) throws SQLException
    {
         Connection conn=DBConnection.getConnection();
         String qry="insert into Users values(?,?,?,?,?)";
         PreparedStatement ps=conn.prepareStatement(qry);
         ps.setString(1,u.getUserId());
         ps.setString(2, name);
         ps.setString(4, u.getPassword());
         ps.setString(3, empId);
         ps.setString(5, u.getUserType());
         int x=ps.executeUpdate();
         if(x==0)
             return false;
        else if(x==1){
            return true;}
        return false;
         
    }
    
         public static Boolean deleteUser(String userId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("delete from users where user_id=?");
    ps.setString(1,userId);
    int x=ps.executeUpdate();
    if(x==0)
    {
        return false;
    }
    else
    {
        return true;
    }
    }
         
        
    
    public static HashMap<String,String> getUser(String userId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("select *from users where user_id=?");
    
    String s=null,s1=null;
    
     HashMap<String,String> productList=new HashMap<>();
       ps.setString(1, userId);
       ResultSet rs=ps.executeQuery();
       while(rs.next())
       {
           s=rs.getString(1);
           s1=rs.getString(2)+","+rs.getString(3);
          productList.put(s, s1);
           
       }
       
return productList;
    
    }
}
