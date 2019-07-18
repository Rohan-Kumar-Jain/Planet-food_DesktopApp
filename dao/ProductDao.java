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

/**
 *
 * @author Dell
 */
public class ProductDao {
    public static String getNewId() throws SQLException
    {
         Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        String str="select count(*) from products";
        ResultSet rs=st.executeQuery(str);
        int id=101;
        if(rs.next())
        {
            id=id+rs.getInt(1);
        }
        
        return "P"+id;
    }


public static boolean getProduct(Product p)throws SQLException
{
 Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into products values(?,?,?,?,?)");
        ps.setString(1, p.getProdId());
        ps.setString(2,p.getCatId());
         ps.setString(3, p.getProdName());
          ps.setInt(4,p.getProdPrice());
           ps.setString(5, p.getIsActive());
           int x=ps.executeUpdate();
           if(x==0)
           {
               return false;
           }else
               return true;
}

public static HashMap<String,Product> getProductsByCategory(String catId) throws SQLException
{
    Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select*from Products where cat_id=?");
       HashMap<String,Product> productList=new HashMap<>();
       ps.setString(1, catId);
       ResultSet rs=ps.executeQuery();
       while(rs.next())
       {
           Product p=new Product();
           p.setCatId(catId);
           p.setProdId(rs.getString("prod_id"));
           p.setProdName(rs.getString("prod_name"));
           p.setProdPrice(rs.getInt("prod_price"));
           p.setIsActive(rs.getString(5));
           productList.put(p.getProdId(), p);
           
       }
       
return productList;
}


public static ArrayList<Product> getAllData()throws SQLException
{
    ArrayList<Product> prdList=new ArrayList<>();
     Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
       ResultSet rs= st.executeQuery("select * from products");
        while(rs.next())
        {
            Product p=new Product();
        p.setProdId(rs.getString(1));
        p.setCatId( rs.getString(2));
          p.setProdName(rs.getString(3));
           p.setProdPrice(rs.getInt(4));
           p.setIsActive(rs.getString(5));
            System.out.println(p.getIsActive());
            prdList.add(p);
        }
         return prdList;
}

public static boolean editProduct(Product p)throws SQLException
{
     Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("update products set prod_name=?,prod_price=?,active=? where prod_id=? and cat_id=?");
   ps.setString(1,p.getProdName());
    ps.setInt(2, p.getProdPrice());
    ps.setString(3, p.getIsActive());
      ps.setString(4,p.getProdId());
      ps.setString(5,p.getCatId() );//System.out.println("fffff");
    int x=ps.executeUpdate();//System.out.println("aaaa");
    if(x==0)
    return false;
    else if(x==1){
    return true;}
    return false;
       
    
    
}

public static boolean deleteProduct(String s1,String s2) throws SQLException
{
      Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("update products set active='N' where prod_name=? and cat_id=?");
    ps.setString(2,s1);
     ps.setString(1,s2);
    int x=ps.executeUpdate();System.out.println("qwewa");
    if(x==0)
    {
        return false;
    }
    else
    {
        return true;
    }
    
    
}
public static HashMap<String,String> getActiveProductsByCategory(String catId) throws SQLException
{
    Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select*from Products where cat_id=? and active='Y'");
       HashMap<String,String> productList=new HashMap<>();
       ps.setString(1, catId);
       ResultSet rs=ps.executeQuery();
       while(rs.next())
       {
           Product p=new Product();
           p.setCatId(catId);
           p.setProdId(rs.getString("prod_id"));
           p.setProdName(rs.getString("prod_name"));
           p.setProdPrice(rs.getInt("prod_price"));
           p.setIsActive(rs.getString(5));
           productList.put(p.getProdId(),p.getProdName() );
           
       }
       
return productList;
}
}



