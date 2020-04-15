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
public class HospitalController implements Serializable {
    private List<hospital> hospitalList;
    private HospitalDao hospitalDao;
    
    private hospital hospital;
    
    public void clearForm(){
        this.setHospital(new hospital());
    }
    
    public void update(){
        this.getHospitalDao().update(this.getHospital());
        this.setHospital(new hospital());
    }
    
    public void updateForm(hospital hospital){
        this.setHospital(hospital);
    }
    
    public void deleteConfirm(hospital hospital){
        this.setHospital(hospital);
    }
    
    public void delete(){
        this.getHospitalDao().delete(this.getHospital());
        this.setHospital(new hospital());
    }
    
    public void create(){
        this.getHospitalDao().insert(this.getHospital());
        this.setHospital(new hospital());
    }

    public List<hospital> getHospitalList() {
        this.hospitalList = this.getHospitalDao().getHospital();
        return hospitalList;
    }

    public void setHospitalList(List<hospital> hospitalList) {
        this.hospitalList = hospitalList;
    }

    public HospitalDao getHospitalDao() {
        if(this.hospitalDao == null)
            this.hospitalDao = new HospitalDao();
        return hospitalDao;
    }

    public void setHospitalDao(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    public hospital getHospital() {
        if(this.hospital == null)
            this.hospital = new hospital();
        return hospital;
    }

    public void setHospital(hospital hospital) {
        this.hospital = hospital;
    }
    
}
