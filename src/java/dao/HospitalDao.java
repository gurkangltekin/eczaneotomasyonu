package dao;

import java.sql.*;
import java.util.*;
import util.DBConnection;
import entity.*;

/**
 *
 * @author gurkangltekin
 */
public class HospitalDao {
        
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        
    public List<hospital> getHospital(){
        List<hospital> hospitalList = new ArrayList();
        
        try{
            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select * from hospital");
            
            while(rs.next()){
                hospital tmp = new hospital(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getDate("last_update"));
                hospitalList.add(tmp);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return hospitalList;
    }

    public void insert(hospital hospital) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into hospital (name, address) values('" + hospital.getName() + "', '" + hospital.getAddress() +"')");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(hospital hospital) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("delete from hospital where id=" + hospital.getId());
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void update(hospital hospital) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("update hospital set name = '" + hospital.getName() + "', dept = " + hospital.getAddress()+ " where id=" + hospital.getId());
            
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
