/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public List<pw> getPw(){
        List<pw> pwList = new ArrayList();
        
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        
        try{
            Statement st = c.createStatement();
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
    
}
