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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.OrderDetails;
import planetfood.pojo.order;

/**
 *
 * @author Dell
 */
public class OrderDao {
    public static ArrayList<order> getOrdersByDate(Date startDate,Date endDate) throws SQLException
    {
       Connection conn=DBConnection.getConnection();
         String qry="select *from orders where ord_date>=? and ord_date<=?";
         PreparedStatement ps=conn.prepareStatement(qry);
         long ms1=startDate.getTime();
         long ms2=endDate.getTime();
         java.sql.Date d1=new java.sql.Date(ms1);
          java.sql.Date d2=new java.sql.Date(ms2);
      
         ps.setDate(1,d1);
         ps.setDate(2, d2);
       //  ps.setDouble(4, e.getSalary());
         //ps.setString(3, e.getJob());
         ResultSet rs=ps.executeQuery();
         ArrayList<order> orderList=new ArrayList<>();
         while(rs.next())
         {
             order obj=new order();
             obj.setOrdId(rs.getString(1));
             SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
             java.sql.Date d=rs.getDate(2);
             String dateStr=sdf.format(d);
             obj.setOrdDate(dateStr);
             obj.setGst(rs.getDouble(3));
             obj.setGstAmount(rs.getDouble(4));
             obj.setGrandTotal(rs.getDouble(6));
             obj.setDiscount(rs.getDouble(5));
             obj.setUserId(rs.getString(7));
             obj.setOrdAmount(rs.getShort(8));
             orderList.add(obj);
         }
         
   return orderList;
    }
    
      public static ArrayList<order> getOrdersByName(Date startDate,Date endDate,String id) throws SQLException
    {
       Connection conn=DBConnection.getConnection();
         String qry="select *from orders where ord_date>=? and ord_date<=? and user_id=?";
         PreparedStatement ps=conn.prepareStatement(qry);
         long ms1=startDate.getTime();
         long ms2=endDate.getTime();
         java.sql.Date d1=new java.sql.Date(ms1);
          java.sql.Date d2=new java.sql.Date(ms2);
      
         ps.setDate(1,d1);
         ps.setDate(2, d2);
         ps.setString(3, id);
       //  ps.setDouble(4, e.getSalary());
         //ps.setString(3, e.getJob());
         ResultSet rs=ps.executeQuery();
         ArrayList<order> orderList=new ArrayList<>();
         while(rs.next())
         {
             order obj=new order();
             obj.setOrdId(rs.getString(1));
             SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
             java.sql.Date d=rs.getDate(2);
             String dateStr=sdf.format(d);
             obj.setOrdDate(dateStr);
             obj.setGst(rs.getDouble(3));
             obj.setGstAmount(rs.getDouble(4));
             obj.setGrandTotal(rs.getDouble(6));
             obj.setDiscount(rs.getDouble(5));
             obj.setUserId(rs.getString(7));
             obj.setOrdAmount(rs.getShort(8));
             orderList.add(obj);
         }
         
   return orderList;
    }
      
      
      
     public static ArrayList<order> getAllOrders() throws SQLException
    {
       Connection conn=DBConnection.getConnection();
         String qry="select *from orders";
         PreparedStatement ps=conn.prepareStatement(qry);
       /*  long ms1=startDate.getTime();
         long ms2=endDate.getTime();
         java.sql.Date d1=new java.sql.Date(ms1);
          java.sql.Date d2=new java.sql.Date(ms2);
      
         ps.setDate(1,d1);
         ps.setDate(2, d2);
      */ //  ps.setDouble(4, e.getSalary());
         //ps.setString(3, e.getJob());
         ResultSet rs=ps.executeQuery();
         ArrayList<order> orderList=new ArrayList<>();
         while(rs.next())
         {
             order obj=new order();
             obj.setOrdId(rs.getString(1));
             SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
             java.sql.Date d=rs.getDate(2);
             String dateStr=sdf.format(d);
             obj.setOrdDate(dateStr);
             obj.setGst(rs.getDouble(3));
             obj.setGstAmount(rs.getDouble(4));
             obj.setGrandTotal(rs.getDouble(6));
             obj.setDiscount(rs.getDouble(5));
             obj.setUserId(rs.getString(7));
             obj.setOrdAmount(rs.getShort(8));
             orderList.add(obj);
         }
         
   return orderList;
    }
    
      public static String getNewId() throws SQLException
    {
         Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        String str="select count(*) from orders";
        ResultSet rs=st.executeQuery(str);
        int id=101;
        if(rs.next())
        {
            id=id+rs.getInt(1);
        }
        
        return "ORD-"+id;

    }
   /*   public static boolean addOrder(order order,ArrayList<OrderDetails> orderList)throws SQLException,ParseException
      {
           Connection conn=DBConnection.getConnection();
       //  String qry="select *from orders";
         PreparedStatement ps=conn.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?)");
         ps.setString(1, order.getOrdId());
         String date=order.getOrdDate();
         SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
         java.util.Date d1=sdf.parse(date);
         long ms1=d1.getTime();
         java.sql.Date sd=new java.sql.Date(ms1);
         ps.setDate(2, sd);
         ps.setDouble(3, order.getGst());
          ps.setDouble(4, order.getGstAmount());
          
       ps.setDouble(5, order.getDiscount());
       ps.setDouble(8, order.getOrdAmount());
       ps.setDouble(6, order.getGrandTotal());
       ps.setString(7, order.getUserId());
          int x=ps.executeUpdate();
          PreparedStatement pss=conn.prepareStatement("insert into order_details values(?,?,?,?)");
          int count=0;int y;
          
     for(OrderDetails od:orderList)
     {
        pss.setString(1, od.getOrdId());
         pss.setString(2, od.getPrdId());
      
          pss.setDouble(3, od.getQuantity());
      
           pss.setDouble(4, od.getCost());
      
     }
     y=pss.executeUpdate();
     if(y>0)
     {
         count=count+y;
     }
     if(count==orderList.size()&&x>0)
     return true;
     else
         return false;
     
     
     
     
     //     return true;
      }*/
      
      
      public static boolean addOrder(order order,ArrayList<OrderDetails> orderList)throws Exception{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into orders values(?,?,?,?,?,?,?,?)");
        ps.setString(1, order.getOrdId());
        String dateStr=order.getOrdDate();
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date d1=sdf.parse(dateStr);
        java.sql.Date d2=new java.sql.Date(d1.getTime());
        ps.setDate(2, d2);
        ps.setDouble(3,order.getGst());
        ps.setDouble(4, order.getGstAmount());
        ps.setDouble(5, order.getDiscount());
        ps.setDouble(6, order.getGrandTotal());
        ps.setString(7,order.getUserId());
        ps.setDouble(8, order.getOrdAmount());
        int x=ps.executeUpdate();
        PreparedStatement ps2=conn.prepareStatement("Insert into order_details values(?,?,?,?)");
        int count=0,y;
        for(OrderDetails detail:orderList)
        {
            ps2.setString(1, detail.getOrdId());
            ps2.setString(2, detail.getPrdId());
            ps2.setDouble(3, detail.getQuantity());
            ps2.setDouble(4, detail.getCost());
            y=ps2.executeUpdate();
            count=count+y;
        }
        if(x>0 && count==orderList.size())
            return true;
        else
            return false;
    }
      
       public static Boolean deleteOrder(String catId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("delete from orders where ord_id=?");
    ps.setString(1,catId);
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

}
