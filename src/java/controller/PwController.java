package controller;

import dao.PwDAO;
import entity.pw;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author gurkangltekin
 */
@Named
@SessionScoped
public class PwController implements Serializable {
    private List<pw> pwList;
    private PwDAO pwDao;
    
    private pw pw;
    
    public void clearForm(){
        this.setPw(new pw());
    }
    
    public void update(){
        this.getPwDao().update(this.getPw());
        this.setPw(new pw());
    }
    
    public void updateForm(pw pW){
        this.setPw(pW);
    }
    
    public void deleteConfirm(pw pW){
        this.setPw(pW);
    }
    
    public void delete(){
        this.getPwDao().delete(this.getPw());
        this.setPw(new pw());
    }
    
    public void create(){
        this.getPwDao().insert(this.getPw());
        this.setPw(new pw());
    }

    public List<pw> getPwList() {
        this.pwList = this.getPwDao().getPw();
        return pwList;
    }

    public void setPwList(List<pw> pwList) {
        this.pwList = pwList;
    }

    public PwDAO getPwDao() {
        if(this.pwDao == null)
            this.pwDao = new PwDAO();
        return pwDao;
    }

    public void setPwDao(PwDAO pwDao) {
        this.pwDao = pwDao;
    }

    public pw getPw() {
        if(this.pw == null)
            this.pw = new pw();
        return pw;
    }

    public void setPw(pw pw) {
        this.pw = pw;
    }
    
}
