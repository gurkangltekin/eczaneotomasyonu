/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import entity.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author gurkangltekin
 */
@Named
@SessionScoped
public class LoginController implements Serializable{
    
    private String username;
    private String password;
    
    private UserDao userDao;
    
    public String login(){
        User user = this.getUserDao().login(this.getUsername(), this.getPassword());
        
        if(user != null){
            if(user.getAuth() == 1){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("eczaci", user);
                return "/secret/secret?faces-redirect=true";
            }else if(user.getAuth() == 0){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("kalfa", user);
                return "/secret/secret?faces-redirect=true";
            }else{
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("kalfa", user);
                return "/index";
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hatali Kullanıcı Adı veya Şifre"));
            return "/login";
        }
    }

    public UserDao getUserDao() {
        if(this.userDao == null){
            this.userDao = new UserDao();
        }
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
