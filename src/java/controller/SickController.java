package controller;

import dao.*;
import entity.*;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author gurkangltekin
 */
@Named
@SessionScoped
public class SickController implements Serializable {
    private List<sick> sickList;
    private SickDao sickDao;
    
    private sick sick;
    
    public void clearForm(){
        this.setSick(new sick());
    }
    
    public void update(){
        this.getSickDao().update(this.getSick());
        this.setSick(new sick());
    }
    
    public void updateForm(sick sick){
        this.setSick(sick);
    }
    
    public void deleteConfirm(sick sick){
        this.setSick(sick);
    }
    
    public void delete(){
        this.getSickDao().delete(this.getSick());
        this.setSick(new sick());
    }
    
    public void create(){
        this.getSickDao().insert(this.getSick());
        this.setSick(new sick());
    }

    public List<sick> getSickList() {
        this.sickList = this.getSickDao().getSick();
        return sickList;
    }

    public void setSickList(List<sick> sickList) {
        this.sickList = sickList;
    }

    public SickDao getSickDao() {
        if(this.sickDao == null)
            this.sickDao = new SickDao();
        return sickDao;
    }

    public void setSickDao(SickDao sickDao) {
        this.sickDao = sickDao;
    }

    public sick getSick() {
        if(this.sick == null)
            this.sick = new sick();
        return sick;
    }

    public void setSick(sick sick) {
        this.sick = sick;
    }
    
}
