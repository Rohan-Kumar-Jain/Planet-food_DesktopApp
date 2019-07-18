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
import java.util.HashMap;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Cate;
import planetfood.pojo.Employee;

/**
 *
 * @author Dell
 */
public class CategoryDao {
    public static HashMap<String,String>getAllCategoryId() throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        String str="select *from categories";
        ResultSet rs=st.executeQuery(str);
        HashMap<String,String> categories=new HashMap<>();
        while(rs.next())
        {
            String catId=rs.getString(1);
            String catName=rs.getString(2);
            categories.put(catName,catId);
            
        }
        return categories;
        
        
    }
    
    
    public static String getNewId() throws SQLException
    {
         Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        String str="select count(*) from categories";
        ResultSet rs=st.executeQuery(str);
        int id=101;
        if(rs.next())
        {
            id=id+rs.getInt(1);
        }
        
        return "C"+id;
    }

    
     public static boolean addCategory(Cate c) throws SQLException
    {
         Connection conn=DBConnection.getConnection();
         String qry="insert into categories values(?,?)";
         PreparedStatement ps=conn.prepareStatement(qry);
      //   Category c=new Category();
          ps.setString(1,c.getCatId());
        ps.setString(2, c.getCatName());
      //   ps.setDouble(4, e.getSalary());
        // ps.setString(3, e.getJob());
         int x=ps.executeUpdate();
         if(x==0)
             return false;
        else if(x==1){
            return true;}
        return false;
         
    }
   
     
       public static boolean updateCategory(Cate c)throws SQLException
    {
         Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("update categories set cat_name=? where cat_id=?");
   ps.setString(1,c.getCatName());
    ps.setString(2, c.getCatId());
    //ps.setDouble(2, e.getSalary());
      //ps.setString(4,e.getEmpId());
    int x=ps.executeUpdate();
    if(x==0)
    return false;
    else if(x==1){
    return true;}
    return false;
       
    }    

       
     public static String getCatIdByName(String name) throws SQLException
    {
        String catid=null; 
        Connection conn=DBConnection.getConnection();
        PreparedStatement st=conn.prepareStatement("select cat_id from categories where cat_name=?");
        //String str="select cat_id from categories where cat_name=?";
        st.setString(1, name);
        ResultSet rs=st.executeQuery();
        
        if(rs.next())
        {
           catid=rs.getString(1);
        }
        
        return catid;
    }
   
}





