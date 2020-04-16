package dao;

import java.sql.Date;
import java.util.*;
import util.DBConnection;
import entity.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gurkangltekin
 */
public class MedicineDao {
        
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        
    public List<medicine> getMedicine(){
        List<medicine> medicineList = new ArrayList();
        
        try{
            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select * from medicine");
            
            while(rs.next()){
                medicine tmp = new medicine(rs.getInt("id"), rs.getString("name"), rs.getDate("exd"), rs.getInt("stock"), rs.getDate("last_update"));
                medicineList.add(tmp);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return medicineList;
    }

    public void insert(medicine medicine) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into medicine (name, exd, stock) values('" + medicine.getName() + "', '" + medicine.getExd() +"', " + medicine.getStock() + ")");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(medicine medicine) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("delete from medicine where id=" + medicine.getId());
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void update(medicine medicine) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("update medicine set name = '" + medicine.getName() + "', exd = '" + medicine.getExd()+ "', stock = " + medicine.getStock() + " where id=" + medicine.getId());
            
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
