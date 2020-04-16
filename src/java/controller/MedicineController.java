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
public class MedicineController implements Serializable {
    private List<medicine> medicineList;
    private MedicineDao medicineDao;
    
    private medicine medicine;
    
    public void clearForm(){
        this.setMedicine(new medicine());
    }
    
    public void update(){
        this.getMedicineDao().update(this.getMedicine());
        this.setMedicine(new medicine());
    }
    
    public void updateForm(medicine medicine){
        this.setMedicine(medicine);
    }
    
    public void deleteConfirm(medicine medicine){
        this.setMedicine(medicine);
    }
    
    public void delete(){
        this.getMedicineDao().delete(this.getMedicine());
        this.setMedicine(new medicine());
    }
    
    public void create(){
        this.getMedicineDao().insert(this.getMedicine());
        this.setMedicine(new medicine());
    }

    public List<medicine> getMedicineList() {
        this.medicineList = this.getMedicineDao().getMedicine();
        return medicineList;
    }

    public void setMedicineList(List<medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public MedicineDao getMedicineDao() {
        if(this.medicineDao == null)
            this.medicineDao = new MedicineDao();
        return medicineDao;
    }

    public void setMedicineDao(MedicineDao medicineDao) {
        this.medicineDao = medicineDao;
    }

    public medicine getMedicine() {
        if(this.medicine == null)
            this.medicine = new medicine();
        return medicine;
    }

    public void setMedicine(medicine medicine) {
        this.medicine = medicine;
    }
    
}
