package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import util.DBConnection;
import entity.pw;

/**
 *
 * @author gurkangltekin
 */
public class PwDAO {
        
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        
    public List<pw> getPw(){
        List<pw> pwList = new ArrayList();
        
        try{
            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select * from pharmaceutical_warehouse");
            
            while(rs.next()){
                pw tmp = new pw(rs.getInt("id"), rs.getString("name"), rs.getInt("dept"), rs.getLong("phone_number"), rs.getDate("last_update"));
                pwList.add(tmp);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return pwList;
    }

    public void insert(pw pw) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into pharmaceutical_warehouse (name, dept, phone_number) values('" + pw.getName() + "', " + pw.getDept() + ", " + pw.getPhone_number() + ")");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(pw pW) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("delete from pharmaceutical_warehouse where id=" + pW.getId());
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void update(pw pW) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("update pharmaceutical_warehouse set name = '" + pW.getName() + "', dept = " + pW.getDept() + ", phone_number = " + pW.getPhone_number() + " where id=" + pW.getId());
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public DBConnection getDb() {
        if(this.db == null)
            this.db = new DBConnection();
        return db;
    }

    public Connection getC() {
        if(this.c == null)
            this.c = this.getDb().connect();
        return c;
    }
    
}
