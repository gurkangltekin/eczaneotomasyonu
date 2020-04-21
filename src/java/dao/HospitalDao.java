package dao;

import java.sql.*;
import java.util.*;
import util.DBConnection;
import entity.*;

/**
 *
 * @author gurkangltekin
 * 
 * Bu Sinifimiz, java tarafindan olusturdugumuz hastane tablomuzun nesnelestirilmis
 * haline veritabanındaki bilgileri eklememizde yardimci olacak.
 */
public class HospitalDao {
    
    /*dbconnection sinifi, bizim tarafimizdan olusturulan veritabani ile haberlesmemizi saglayan, 
    veritabani serverimiza baglanmamizi saglayacak bilgilerin bulundugu siniftir.*/
    DBConnection db = new DBConnection();
    Connection c = db.connect();
    
    public hospital find(int id){
        hospital h = null;
        
        try{
            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select * from hospital where id = " + id);
            rs.next();
            h = new hospital(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getDate("last_update"));
                
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return h;
    }
        
    /*bu metodumuz veritabanındaki hastane tablomuzdas bulunan tum satirlari
    *burada liste haline getirerek somutlastirma islemini gerceklestiriyoruz
    */
    public List<hospital> getHospital(){
        List<hospital> hospitalList = new ArrayList();
        
        try{
            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select * from hospital");
            
            while(rs.next()){
                hospital tmp = new hospital(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getDate("last_update"));
                hospitalList.add(tmp);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return hospitalList;
    }

    /*Bu metodumuz hastane tablosuna yeni hastane bilgilerinin girislerini
    gerceklestirebilmemiz icin gerekli kod parcaciklarini barindiriyor.
    */
    public void insert(hospital hospital) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into hospital (name, address) values('" + hospital.getName() + "', '" + hospital.getAddress() +"')");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*Bu metodumuz hastane tablomuzda yanlis girilen veya artik veritabanimizda
    bulunmasini gerektirmeyecek hastane bilgilerini silmemize yariyor.*/
    public void delete(hospital hospital) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("delete from hospital where id=" + hospital.getId());
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    /*Bu metodumuz, tablomuzda yanlis girilen veya bilgisi degisen bir hastane
    bilgisinin guncellenmesini gerceklestiren kod parcaciklarini barindiriyor.*/
    public void update(hospital hospital) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("update hospital set name = '" + hospital.getName() + "', address = '" + hospital.getAddress()+ "' where id=" + hospital.getId());
            
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
    
}
