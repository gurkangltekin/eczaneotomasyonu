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
 * bu sinifimiz, hasta nesnemizin ve veritabanindaki ilac verilerini arayuzde
 * goruleyebilmemiz icin nesne ve arayuz arasinda haberlesme ve kontrol gorevi goruyor.
 */
@Named
@SessionScoped
public class SickController implements Serializable {
    /*ilac listesi, gelen tum hasta bilgilerinin bir liste halinde tutulup
    ekrana yazdirilabilmesi icin controller sinifimizda da barindiriliyor.*/
    private List<sick> sickList;
    
    /*hasta tablosundan verileri cekebilmek ve yeni veri ekleyip veri guncelleyebilmek
    icin hastadao sinifi controller sinifimizda tanimlamamiz gerekiyor.*/
    private SickDao sickDao;
    
    /*selectedSick degiskeni, ilac satilacak olan hastanın belirlenmesi ve
    gerekli satis isleminin veritabanina dogru hasta is'si ile kayıt edilmesi icin
    kullaniliyor. arayuzde secilen hastanin id bilgisini tutacak.*/
    private int selectedSick;
    
    /*arayuzde ilac satilacak olan hastaya hangi ilaclarin satilacagini sectikten
    secilen ilacların id'si bu listeye kayit edilecek ve hasta_ilac tablosunda 
    kaydedilmek uzere gunderilecek.*/
    private List<Integer> selectedMedicines;
    
    /*mevcut hasta varligini for uzerinden duzenleyebilmek veya yeni hasta varligini
    form uzerinden alip veritabanina ekleyebilmek icin icin 1 adet tekil hasta
    nesnesine ihtiyacimiz bulunmakta.*/
    private sick sick;
    
    /*ilac satma islemlerinde ilaclarin listelenmesi, goruntulenmesi islemlerine
    ihtiyac duyacagimizdan bu metodlarin tanimli olmasi gerek. medicineController
     sinifinda bu metod ve ozellikler tanimli oldugu icin o sinifi ozellikleriyle
    beraber inject ederek kullaniyoruz.*/
    @Inject
    private MedicineController medicineController;
    
    
    /*bir hastaya ilac satisi yapildigi zaman Sat butonuna basildiginda bu metod
    devreye girecek ve bu metod araciligirla secilen hastaya secilen ilaclarin
    satildigi bilgisi hasta_ilac tablosuna kayit edilecek.*/
    public void sell(){
        this.getSickDao().sell(this.selectedSick, this.selectedMedicines);
        this.selectedMedicines = null;
        this.selectedSick = 0;
    }
    
    /*form uzerine yazilmis bilgilere artik ihtiyacimiz yoksa bu metod ile
    form uzerindeki verilerin temizlenmesi islemini gerceklestiriyoruz*/
    public void clearForm(){
        this.setSick(new sick());
    }
    
    /*form uzerindeki hasta bilgilerinde degisiklikler yapilip guncelle
    butonuna basildiysa bu metodumuz calisacak ve hastadao sinifimizdaki guncelle
    metoduna bilgileri gondererek veritabanindaki bilgilerin degistirilmesini
    saglayan kod parcalarinin baslangic kismidir.*/
    public void update(){
        this.getSickDao().update(this.getSick());
        this.setSick(new sick());
    }
    
    /*bir hasta bilgisinin guncellenmek uzere forma aktarilmasi islemini
    gerceklestiren metoddur.*/
    public void updateForm(sick sick){
        this.setSick(sick);
    }
    
    /*hasta bilgisinin silinmesinin onayının alinmasi icin acilacak olan
    modal penceresine silinmesi planlanan hasta bilgilerini yazan metod.*/
    public void deleteConfirm(sick sick){
        this.setSick(sick);
    }
    
    /*silinecek olan hasta bilgisinin modal uzerinden onayi alinmasi durumunda
    veritabanindan silinmesi islemini gerceklestirecek olan metod*/
    public void delete(){
        this.getSickDao().delete(this.getSick());
        this.setSick(new sick());
    }
    
    /*bos bir form doldurularak yeni bir hasta bilgisi girilmek isteniyorsa 
    olustur butonuna bastiktan sonra bu metod araciligiyla dao sinifimiza 
    bilgileri gondererek insert isleminin baslamisini saglayan metoddur.*/
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

    public int getSelectedSick() {
        return selectedSick;
    }

    public void setSelectedSick(int selectedSick) {
        this.selectedSick = selectedSick;
    }

    public List<Integer> getSelectedMedicines() {
        return selectedMedicines;
    }

    public void setSelectedMedicines(List<Integer> selectedMedicines) {
        this.selectedMedicines = selectedMedicines;
    }

    public MedicineController getMedicineController() {
        return medicineController;
    }

    public void setMedicineController(MedicineController medicineController) {
        this.medicineController = medicineController;
    }
    
}
