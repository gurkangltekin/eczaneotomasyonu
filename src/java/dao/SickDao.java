package dao;

import java.sql.*;
import java.util.*;
import util.DBConnection;
import entity.*;

/**
 *
 * @author gurkangltekin
 * 
 * Bu Sinifimiz, java tarafindan olusturdugumuz hasta tablomuzun nesnelestirilmis
 * haline veritabanındaki bilgileri eklememizde yardimci olacak.
 */
public class SickDao {
        
    /*dbconnection sinifi, bizim tarafimizdan olusturulan veritabani ile haberlesmemizi saglayan, 
    veritabani serverimiza baglanmamizi saglayacak bilgilerin bulundugu siniftir.*/
    DBConnection db = new DBConnection();
    Connection c = db.connect();
    
    /*ilacdao sinifimizin nesnesinin hasta sinifinda olmasinin sebebi, her hastanin
    aldigi ilaclarin bilgisini hasta içerisinde barindirabilmek icin ilacdao 
    sinifini hastadao sinifimiza entegre etme ihtiyaci doguyor.*/
    private MedicineDao mDao;
    
    public sick find(int id){
        sick s = null;
        
        return s;
    }
        
    /*bu metodumuz veritabanındaki hasta tablomuzdas bulunan tum satirlari
    *burada liste haline getirerek somutlastirma islemini gerceklestiriyoruz
    */
    public List<sick> getSick(){
        List<sick> sickList = new ArrayList();
        
        try{
            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select * from sick");
            
            while(rs.next()){
                sick tmp = new sick(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getDate("last_update"));
                tmp.setMedicines(this.getmDao().getSickMedicines(rs.getInt("id")));
                sickList.add(tmp);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return sickList;
    }

    /*Bu metodumuz hasta tablosuna yeni ilac bilgilerinin girislerini
    gerceklestirebilmemiz icin gerekli kod parcaciklarini barindiriyor.
    */
    public void insert(sick sick) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into sick (name, surname) values('" + sick.getName() + "', '" + sick.getSurname()+"')");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*Bu metodumuz hasta tablomuzda yanlis girilen veya artik veritabanimizda
    bulunmasini gerektirmeyecek hasta bilgilerini silmemize yariyor.*/
    public void delete(sick sick) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("delete from sick where id=" + sick.getId());
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*Bu metodumuz, tablomuzda yanlis girilen veya bilgisi degisen bir hasta
    bilgisinin guncellenmesini gerceklestiren kod parcaciklarini barindiriyor.*/
    public void update(sick sick) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("update sick set name = '" + sick.getName() + "', surname = '" + sick.getSurname()+ "' where id=" + sick.getId());
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*bu metodumuz, eczane tarafindan bir hastaya satilan ilaclarin hasta_ilac 
    tablosuna veri girisinin yapilmasini, kayit altina alinmasini 
    gerceklestiriliyor. her hastanin aldigi her ilaci bu metod uzerinden veri 
    tabanina kaydediyoruz.*/
    public void sell(int selectedSick, List<Integer> selectedMedicines) {
        try{
            Statement st = this.getC().createStatement();
            
            int size = selectedMedicines.size();
            
            for(int i = 0 ; i < size ; i++){
                st.executeUpdate("insert into sick_medicine (sick, medicine_id) values (" + selectedSick + ", " + selectedMedicines.get(i) + ")");
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public DBConnection getDb() {
        //nesnemizin null gelme olasiligini ortadan kaldirmak amaciyla kontrol gerceklestiriyoruz
        if(this.db == null)
            this.db = new DBConnection();
        return db;
    }

    public Connection getC() {
        //nesnemizin null gelme olasiligini ortadan kaldirmak amaciyla kontrol gerceklestiriyoruz
        if(this.c == null)
            this.c = this.getDb().connect();
        return c;
    }

    public MedicineDao getmDao() {
        //nesnemizin null gelme olasiligini ortadan kaldirmak amaciyla kontrol gerceklestiriyoruz
        if(this.mDao == null)
            this.mDao = new MedicineDao();
        return mDao;
    }
    
}
