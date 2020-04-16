package dao;

import java.sql.*;
import java.util.*;
import util.DBConnection;
import entity.*;

/**
 *
 * @author gurkangltekin
 */
public class SickDao {
        
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        
    public List<sick> getSick(){
        List<sick> sickList = new ArrayList();
        
        try{
            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select * from sick");
            
            while(rs.next()){
                sick tmp = new sick(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getDate("last_update"));
                sickList.add(tmp);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return sickList;
    }

    public void insert(sick sick) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into sick (name, surname) values('" + sick.getName() + "', '" + sick.getSurname()+"')");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(sick sick) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("delete from sick where id=" + sick.getId());
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void update(sick sick) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("update sick set name = '" + sick.getName() + "', surname = '" + sick.getSurname()+ "' where id=" + sick.getId());
            
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
