package controller;

import dao.PwDAO;
import entity.pw;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author gurkangltekin
 * 
 * bu sinifimiz, ecza deposu nesnemizin ve veritabanindaki ilac verilerini arayuzde
 * goruleyebilmemiz icin nesne ve arayuz arasinda haberlesme ve kontrol gorevi goruyor.
 */
@Named
@SessionScoped
public class PwController implements Serializable {
    /*ecza deposu listesi, gelen tum ecza deposu bilgilerinin bir liste halinde tutulup
    ekrana yazdirilabilmesi icin controller sinifimizda da barindiriliyor.*/
    private List<pw> pwList;
    
    /*ecza deposu tablosundan verileri cekebilmek ve yeni veri ekleyip veri guncelleyebilmek
    icin ecza deposudao sinifi controller sinifimizda tanimlamamiz gerekiyor.*/
    private PwDAO pwDao;
    
    /*mevcut ecza deposu varligini for uzerinden duzenleyebilmek veya yeni ecza 
    deposu varligini form uzerinden alip veritabanina ekleyebilmek icin icin 
    1 adet tekil ecza deposu nesnesine ihtiyacimiz bulunmakta.*/
    private pw pw;
    
    /*form uzerine yazilmis bilgilere artik ihtiyacimiz yoksa bu metod ile
    form uzerindeki verilerin temizlenmesi islemini gerceklestiriyoruz*/
    public void clearForm(){
        this.setPw(new pw());
    }
    
    /*form uzerindeki ecza deposu bilgilerinde degisiklikler yapilip guncelle
    butonuna basildiysa bu metodumuz calisacak ve eczadeposudao sinifimizdaki guncelle
    metoduna bilgileri gondererek veritabanindaki bilgilerin degistirilmesini
    saglayan kod parcalarinin baslangic kismidir.*/
    public void update(){
        this.getPwDao().update(this.getPw());
        this.setPw(new pw());
    }
    
    /*bir ecza deposu bilgisinin guncellenmek uzere forma aktarilmasi islemini
    gerceklestiren metoddur.*/
    public void updateForm(pw pW){
        this.setPw(pW);
    }
    
    /*ecza deposu bilgisinin silinmesinin onayının alinmasi icin acilacak olan
    modal penceresine silinmesi planlanan ecza deposu bilgilerini yazan metod.*/
    public void deleteConfirm(pw pW){
        this.setPw(pW);
    }
    
    /*silinecek olan ecza deposu bilgisinin modal uzerinden onayi alinmasi durumunda
    veritabanindan silinmesi islemini gerceklestirecek olan metod*/
    public void delete(){
        this.getPwDao().delete(this.getPw());
        this.setPw(new pw());
    }
    
    /*bos bir form doldurularak yeni bir ilac bilgisi girilmek isteniyorsa 
    olustur butonuna bastiktan sonra bu metod araciligiyla dao sinifimiza 
    bilgileri gondererek insert isleminin baslamisini saglayan metoddur.*/
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
