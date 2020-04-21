package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import util.DBConnection;
import entity.pw;

/**
 *
 * @author gurkangltekin
 * 
 * Bu Sinifimiz, java tarafindan olusturdugumuz ecza deposu tablomuzun nesnelestirilmis
 * haline veritabanındaki bilgileri eklememizde yardimci olacak.
 */
public class PwDAO {
        
    /*dbconnection sinifi, bizim tarafimizdan olusturulan veritabani ile haberlesmemizi saglayan, 
    veritabani serverimiza baglanmamizi saglayacak bilgilerin bulundugu siniftir.*/
    DBConnection db = new DBConnection();
    Connection c = db.connect();
        
    
    /*bu metodumuz veritabanındaki ecza deposu tablomuzdas bulunan tum satirlari
    *burada liste haline getirerek somutlastirma islemini gerceklestiriyoruz
    */
    public List<pw> getPw(){
        List<pw> pwList = new ArrayList();
        
        try{
            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select * from pharmaceutical_warehouse");
            
            while(rs.next()){
                pw tmp = new pw(rs.getInt("id"), rs.getString("name"), rs.getInt("dept"), rs.getLong("phone_number"), rs.getDate("last_update"));
                pwList.add(tmp);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return pwList;
    }

    /*Bu metodumuz ecza deposu tablosuna yeni ilac bilgilerinin girislerini
    gerceklestirebilmemiz icin gerekli kod parcaciklarini barindiriyor.
    */
    public void insert(pw pw) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into pharmaceutical_warehouse (name, dept, phone_number) values('" + pw.getName() + "', " + pw.getDept() + ", " + pw.getPhone_number() + ")");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*Bu metodumuz ecza deposu tablomuzda yanlis girilen veya artik veritabanimizda
    bulunmasini gerektirmeyecek ecza deposu bilgilerini silmemize yariyor.*/
    public void delete(pw pW) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("delete from pharmaceutical_warehouse where id=" + pW.getId());
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*Bu metodumuz, tablomuzda yanlis girilen veya bilgisi degisen bir ecza deposu
    bilgisinin guncellenmesini gerceklestiren kod parcaciklarini barindiriyor.*/
    public void update(pw pW) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("update pharmaceutical_warehouse set name = '" + pW.getName() + "', dept = " + pW.getDept() + ", phone_number = " + pW.getPhone_number() + " where id=" + pW.getId());
            
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
