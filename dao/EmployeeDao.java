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
import planetfood.pojo.Employee;

/**
 *
 * @author Dell
 */
public class EmployeeDao {
    
    
      public static String getNewId() throws SQLException
    {
         Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        String str="select count(*) from employees";
        ResultSet rs=st.executeQuery(str);
        int id=101;
        if(rs.next())
        {
            id=id+rs.getInt(1);
        }
        
        return "e"+id;
    }
    
    public static boolean addEmployee(Employee e) throws SQLException
    {
         Connection conn=DBConnection.getConnection();
         String qry="insert into employees values(?,?,?,?)";
         PreparedStatement ps=conn.prepareStatement(qry);
         ps.setString(1,e.getEmpId());
         ps.setString(2, e.getEmpName());
         ps.setDouble(4, e.getSalary());
         ps.setString(3, e.getJob());
         int x=ps.executeUpdate();
         if(x==0)
             return false;
        else if(x==1){
            return true;}
        return false;
         
    }
    
    public static ArrayList<String> getAllEmployee() throws SQLException    //static Collection
    {
        ArrayList<String> emplist=new ArrayList<String>();
       Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("select *from employees");
    while(rs.next())
    {  
        
        String s=rs.getString(1);
      //  e.setJob(rs.getString(3));
       // e.setSalary(rs.getDouble(4));
        emplist.add(s);
    }
    return emplist;
    }
    
     public static boolean updateEmployee(Employee e)throws SQLException
    {
         Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("update employees set e_name=?,sal=?,job=? where emp_id=?");
   ps.setString(1,e.getEmpName());
    ps.setString(3, e.getJob());
    ps.setDouble(2, e.getSalary());
      ps.setString(4,e.getEmpId());
    int x=ps.executeUpdate();
    if(x==0)
    return false;
    else if(x==1){
    return true;}
    return false;
       
    }    
     
     
      public static Boolean deleteEmployee(String empId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("delete from employees where emp_id=?");
    ps.setString(1,empId);
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
    
      
      public static ArrayList<Employee> getAllData()throws SQLException
{
    ArrayList<Employee> prdList=new ArrayList<>();
     Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
       ResultSet rs= st.executeQuery("select * from employees");
        while(rs.next())
        {
            Employee e=new Employee();
            e.setEmpId(rs.getString(1));
     //   p.setProdId(rs.getString(1));
       e.setEmpName(rs.getString(2));
     //p.setCatId( rs.getString(2));
       e.setJob(rs.getString(3));
     //p.setProdName(rs.getString(3));
       e.setSalary(rs.getDouble(4));
     //p.setProdPrice(rs.getInt(4));
       
     //p.setIsActive(rs.getString(5));
             prdList.add(e);
        }
         return prdList;
}

      
      
       public static HashMap<String,String> getEmployee() throws SQLException    //static Collection
    {
        HashMap<String,String> emplist=new HashMap<>();
       Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("select *from employees where job='cashier'");
    while(rs.next())
    {  
        
       // String s=rs.getString(1);
        //String s=rs.getString(2);
        
      //  e.setJob(rs.getString(3));
       // e.setSalary(rs.getDouble(4));
        emplist.put(rs.getString(1), rs.getString(2));
    }
    return emplist;
    }
}













