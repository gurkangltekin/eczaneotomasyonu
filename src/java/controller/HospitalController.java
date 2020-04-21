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
 * 
 * bu sinifimiz, hastane nesnemizin ve veritabanindaki hastane verilerini arayuzde
 * goruleyebilmemiz icin nesne ve arayuz arasinda haberlesme ve kontrol gorevi goruyor.
 */
@Named
@SessionScoped
public class HospitalController implements Serializable {
    /*hastane listesi, gelen tum hastane bilgilerinin bir liste halinde tutulup
    ekrana yazdirilabilmesi icin controller sinifimizda da barindiriliyor.*/
    private List<hospital> hospitalList;
    
    /*hastane tablosundan verileri cekebilmek ve yeni veri ekleyip veri guncelleyebilmek
    icin hastanedao sinifi controller sinifimizda tanimlamamiz gerekiyor.*/
    private HospitalDao hospitalDao;
    
    /*mevcut hastane varligini for uzerinden duzenleyebilmek veya yeni hastane varligini
    form uzerinden alip veritabanina ekleyebilmek icin icin 1 adet tekil hastane
    nesnesine ihtiyacimiz bulunmakta.*/
    private hospital hospital;
    
    /*form uzerine yazilmis bilgilere artik ihtiyacimiz yoksa bu metod ile
    form uzerindeki verilerin temizlenmesi islemini gerceklestiriyoruz*/
    public void clearForm(){
        this.setHospital(new hospital());
    }
    /*form uzerindeki hastane bilgilerinde degisiklikler yapilip guncelle
    butonuna basildiysa bu metodumuz calisacak ve hastanedao sinifimizdaki guncelle
    metoduna bilgileri gondererek veritabanindaki bilgilerin degistirilmesini
    saglayan kod parcalarinin baslangic kismidir.*/
    public void update(){
        this.getHospitalDao().update(this.getHospital());
        this.setHospital(new hospital());
    }
    
    /*bir hastane bilgisinin guncellenmek uzere forma aktarilmasi islemini
    gerceklestiren metoddur.*/
    public void updateForm(hospital hospital){
        this.setHospital(hospital);
    }
    
    /*hastane bilgisinin silinmesinin onayının alinmasi icin acilacak olan
    modal penceresine silinmesi planlanan hastane bilgilerini yazan metod.*/
    public void deleteConfirm(hospital hospital){
        this.setHospital(hospital);
    }
    
    /*silinecek olan hastane bilgisinin modal uzerinden onayi alinmasi durumunda
    veritabanindan silinmesi islemini gerceklestirecek olan metod*/
    public void delete(){
        this.getHospitalDao().delete(this.getHospital());
        this.setHospital(new hospital());
    }
    
    /*bos bir form doldurularak yeni bir hastane bilgisi girilmek isteniyorsa 
    olustur butonuna bastiktan sonra bu metod araciligiyla dao sinifimiza 
    bilgileri gondererek insert isleminin baslamisini saglayan metoddur.*/
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
