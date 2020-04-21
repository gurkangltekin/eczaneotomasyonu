package controller;

import dao.*;
import entity.*;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author gurkangltekin
 * 
 * bu sinifimiz, doktor nesnemizin ve veritabanindaki doktor verilerini arayuzde
 * goruleyebilmemiz icin nesne ve arayuz arasinda haberlesme ve kontrol gorevi goruyor.
 */
@Named
@SessionScoped
public class DoctorController implements Serializable {
    /*doktor listesi, gelen tum doktor bilgilerinin bir liste halinde tutulup
    ekrana yazdirilabilmesi icin controller sinifimizda da barindiriliyor.*/
    private List<doctor> doctorList;
    
    /*doktor tablosundan verileri cekebilmek ve yeni veri ekleyip veri guncelleyebilmek
    icin doktordao sinifi controller sinifimizda tanimlamamiz gerekiyor.*/
    private DoctorDao doctorDao;
    
    /*mevcut doktor varligini for uzerinden duzenleyebilmek veya yeni doktor varligini
    form uzerinden alip veritabanina ekleyebilmek icin icin 1 adet tekil doktor
    nesnesine ihtiyacimiz bulunmakta.*/
    private doctor doctor;
    
    private int selectedHospital;
    
    @Inject
    private HospitalController hospitalController;
    
    /*form uzerine yazilmis bilgilere artik ihtiyacimiz yoksa bu metod ile
    form uzerindeki verilerin temizlenmesi islemini gerceklestiriyoruz*/
    public void clearForm(){
        this.setDoctor(new doctor());
    }
    /*form uzerindeki doktor bilgilerinde degisiklikler yapilip guncelle
    butonuna basildiysa bu metodumuz calisacak ve doktordao sinifimizdaki guncelle
    metoduna bilgileri gondererek veritabanindaki bilgilerin degistirilmesini
    saglayan kod parcalarinin baslangic kismidir.*/
    public void update(){
        this.getDoctorDao().update(this.getDoctor(), selectedHospital);
        this.setDoctor(new doctor());
    }
    
    /*bir doktor bilgisinin guncellenmek uzere forma aktarilmasi islemini
    gerceklestiren metoddur.*/
    public void updateForm(doctor doctor){
        this.setDoctor(doctor);
    }
    
    /*doktor bilgisinin silinmesinin onayının alinmasi icin acilacak olan
    modal penceresine silinmesi planlanan doktor bilgilerini yazan metod.*/
    public void deleteConfirm(doctor doctor){
        this.setDoctor(doctor);
    }
    
    /*silinecek olan doktor bilgisinin modal uzerinden onayi alinmasi durumunda
    veritabanindan silinmesi islemini gerceklestirecek olan metod*/
    public void delete(){
        this.getDoctorDao().delete(this.getDoctor());
        this.setDoctor(new doctor());
    }
    
    /*bos bir form doldurularak yeni bir doktor bilgisi girilmek isteniyorsa 
    olustur butonuna bastiktan sonra bu metod araciligiyla dao sinifimiza 
    bilgileri gondererek insert isleminin baslamisini saglayan metoddur.*/
    public void create(){
        this.getDoctorDao().insert(this.getDoctor(), selectedHospital);
        this.setDoctor(new doctor());
    }

    public List<doctor> getDoctorList() {
        this.doctorList = this.getDoctorDao().getDoctor();
        return doctorList;
    }

    public void setDoctorList(List<doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public DoctorDao getDoctorDao() {
        if(this.doctorDao == null)
            this.doctorDao = new DoctorDao();
        return doctorDao;
    }

    public void setDoctorDao(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    public doctor getDoctor() {
        if(this.doctor == null)
            this.doctor = new doctor();
        return doctor;
    }

    public void setDoctor(doctor doctor) {
        this.doctor = doctor;
    }

    public HospitalController getHospitalController() {
        return hospitalController;
    }

    public void setHospitalController(HospitalController hospitalController) {
        this.hospitalController = hospitalController;
    }

    public int getSelectedHospital() {
        return selectedHospital;
    }

    public void setSelectedHospital(int selectedHospital) {
        this.selectedHospital = selectedHospital;
    }
    
    
}
