/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gurkangltekin
 */
public class UserDao extends dao{
    
    public User login(String username, String password){
        User user = null;
        
        try{
            
            PreparedStatement pst = this.getC().prepareStatement("select * from users where username = ? and password = ?");
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                
                user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setAuth(rs.getInt("auth"));
                
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return user;
    }

    @Override
    void delete(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void insert(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void update(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
