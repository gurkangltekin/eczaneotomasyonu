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
 * bu sinifimiz, ilac nesnemizin ve veritabanindaki ilac verilerini arayuzde
 * goruleyebilmemiz icin nesne ve arayuz arasinda haberlesme ve kontrol gorevi goruyor.
 */
@Named
@SessionScoped
public class MedicineController implements Serializable {
    /*ilac listesi, gelen tum ilac bilgilerinin bir liste halinde tutulup
    ekrana yazdirilabilmesi icin controller sinifimizda da barindiriliyor.*/
    private List<medicine> medicineList;
    
    /*ilac tablosundan verileri cekebilmek ve yeni veri ekleyip veri guncelleyebilmek
    icin ilacdao sinifi controller sinifimizda tanimlamamiz gerekiyor.*/
    private MedicineDao medicineDao;
    
    /*mevcut ilac varligini for uzerinden duzenleyebilmek veya yeni ilac varligini
    form uzerinden alip veritabanina ekleyebilmek icin icin 1 adet tekil ilac
    nesnesine ihtiyacimiz bulunmakta.*/
    private medicine medicine;
    
    /*form uzerine yazilmis bilgilere artik ihtiyacimiz yoksa bu metod ile
    form uzerindeki verilerin temizlenmesi islemini gerceklestiriyoruz*/
    public void clearForm(){
        this.setMedicine(new medicine());
    }
    
    /*form uzerindeki ilac bilgilerinde degisiklikler yapilip guncelle
    butonuna basildiysa bu metodumuz calisacak ve hastanedao sinifimizdaki guncelle
    metoduna bilgileri gondererek veritabanindaki bilgilerin degistirilmesini
    saglayan kod parcalarinin baslangic kismidir.*/
    public void update(){
        this.getMedicineDao().update(this.getMedicine());
        this.setMedicine(new medicine());
    }
    
    /*bir ilac bilgisinin guncellenmek uzere forma aktarilmasi islemini
    gerceklestiren metoddur.*/
    public void updateForm(medicine medicine){
        this.setMedicine(medicine);
    }
    
    /*ilac bilgisinin silinmesinin onayının alinmasi icin acilacak olan
    modal penceresine silinmesi planlanan ilac bilgilerini yazan metod.*/
    public void deleteConfirm(medicine medicine){
        this.setMedicine(medicine);
    }
    
    /*silinecek olan ilac bilgisinin modal uzerinden onayi alinmasi durumunda
    veritabanindan silinmesi islemini gerceklestirecek olan metod*/
    public void delete(){
        this.getMedicineDao().delete(this.getMedicine());
        this.setMedicine(new medicine());
    }
    
    /*bos bir form doldurularak yeni bir ilac bilgisi girilmek isteniyorsa 
    olustur butonuna bastiktan sonra bu metod araciligiyla dao sinifimiza 
    bilgileri gondererek insert isleminin baslamisini saglayan metoddur.*/
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
