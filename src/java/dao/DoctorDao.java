package dao;

import java.sql.*;
import java.util.*;
import util.DBConnection;
import entity.*;

/**
 *
 * @author gurkangltekin
 * 
 * Bu Sinifimiz, java tarafindan olusturdugumuz doktor tablomuzun nesnelestirilmis
 * haline veritabanındaki bilgileri eklememizde yardimci olacak.
 */
public class DoctorDao {
        
    /*dbconnection sinifi, bizim tarafimizdan olusturulan veritabani ile haberlesmemizi saglayan, 
    veritabani serverimiza baglanmamizi saglayacak bilgilerin bulundugu siniftir.*/
    DBConnection db = new DBConnection();
    Connection c = db.connect();
    
    HospitalDao hDao = new HospitalDao();
    
    
    public doctor find(int id){
        doctor d = null;
        
        try{
            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select * from doctor where id = " + id);
            rs.next();
            d = new doctor(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("branch"), rs.getDate("last_update"));
            d.setHospital(this.gethDao().find(rs.getInt("hospital")));
                
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return d;
    }
        
    /*bu metodumuz veritabanındaki hasta tablomuzdas bulunan tum satirlari
    *burada liste haline getirerek somutlastirma islemini gerceklestiriyoruz
    */
    public List<doctor> getDoctor(){
        List<doctor> doctorList = new ArrayList();
        
        try{
            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select * from doctor");
            
            while(rs.next()){
                doctor tmp = new doctor(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("branch"), rs.getDate("last_update"));
                tmp.setHospital(this.hDao.find(rs.getInt("hospital")));
                doctorList.add(tmp);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return doctorList;
    }

    /*Bu metodumuz hasta tablosuna yeni ilac bilgilerinin girislerini
    gerceklestirebilmemiz icin gerekli kod parcaciklarini barindiriyor.
    */
    public void insert(doctor doctor, int selectedHospital) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into doctor (name, surname, branch, hospital) values('" + doctor.getName() + "', '" + doctor.getSurname()+"', '" + doctor.getBranch() + "', " + selectedHospital + ")");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*Bu metodumuz hasta tablomuzda yanlis girilen veya artik veritabanimizda
    bulunmasini gerektirmeyecek hasta bilgilerini silmemize yariyor.*/
    public void delete(doctor doctor) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("delete from doctor where id = " + doctor.getId());
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*Bu metodumuz, tablomuzda yanlis girilen veya bilgisi degisen bir hasta
    bilgisinin guncellenmesini gerceklestiren kod parcaciklarini barindiriyor.*/
    public void update(doctor doctor, int selectedHospital) {
        
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("update doctor set name = '" + doctor.getName() + "', surname = '" + doctor.getSurname()+ "', branch = '" + doctor.getBranch() + "', hospital = " + selectedHospital + " where id=" + doctor.getId());
            
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

    public HospitalDao gethDao() {
        //nesnemizin null gelme olasiligini ortadan kaldirmak amaciyla kontrol gerceklestiriyoruz
        if(this.hDao == null)
            this.hDao = new HospitalDao();
        return hDao;
    }
    
}
