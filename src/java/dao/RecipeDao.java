package dao;

import java.sql.*;
import java.util.*;
import entity.*;

/**
 *
 * @author gurkangltekin
 * 
 * Bu Sinifimiz, java tarafindan olusturdugumuz hasta tablomuzun nesnelestirilmis
 * haline veritabanındaki bilgileri eklememizde yardimci olacak.
 */
public class RecipeDao extends dao{
    
    /*ilacdao sinifimizin nesnesinin hasta sinifinda olmasinin sebebi, her hastanin
    aldigi ilaclarin bilgisini hasta içerisinde barindirabilmek icin ilacdao 
    sinifini hastadao sinifimiza entegre etme ihtiyaci doguyor.*/
    private MedicineDao mDao;
    
    private SickDao sDao;
    private DoctorDao dDao;
        
    /*bu metodumuz veritabanındaki hasta tablomuzdas bulunan tum satirlari
    *burada liste haline getirerek somutlastirma islemini gerceklestiriyoruz
    */
    public List<recipe> getRecipe(){
        List<recipe> recipeList = new ArrayList();
        
        try{
            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select * from recipe");
            
            while(rs.next()){
                recipe tmp = new recipe();
                tmp.setDate(rs.getDate("date"));
                tmp.setDiagnosis(rs.getString("diagnosis"));
                tmp.setSick(this.getsDao().find(rs.getInt("sick_id")));
                tmp.setDoctor(this.getdDao().find(rs.getInt("doctor_id")));
                tmp.setMedicines(this.getmDao().getRecipeMedicines(rs.getInt("id")));
                recipeList.add(tmp);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return recipeList;
    }

    /*Bu metodumuz hasta tablosuna yeni ilac bilgilerinin girislerini
    gerceklestirebilmemiz icin gerekli kod parcaciklarini barindiriyor.
    */
    @Override
    public void insert(Object obj, int selectedHospital) {
        recipe recipe = (recipe)obj;
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("insert into recipe (date, diagnosis, sick_id, doctor_id) values('" 
                    + recipe.getDate()+ "', '" + recipe.getDiagnosis() + "', " 
                    + recipe.getSick().getId() + ", " + recipe.getDoctor().getId() + ")");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*Bu metodumuz hasta tablomuzda yanlis girilen veya artik veritabanimizda
    bulunmasini gerektirmeyecek hasta bilgilerini silmemize yariyor.*/
    @Override
    public void delete(Object obj) {
        recipe recipe = (recipe)obj;
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("delete from recipe where id=" + recipe.getId());
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*Bu metodumuz, tablomuzda yanlis girilen veya bilgisi degisen bir hasta
    bilgisinin guncellenmesini gerceklestiren kod parcaciklarini barindiriyor.*/
    @Override
    public void update(Object obj, int selected) {
        recipe recipe = (recipe)obj;
        try{
            Statement st = this.getC().createStatement();
            st.executeUpdate("update recipe set date = '" + recipe.getDate() 
                    + "', diagnosis = '" + recipe.getDiagnosis()+ "', sick_id = " 
                    + recipe.getSick().getId() + ", doctor_id" + recipe.getDoctor().getId() 
                    + " where id=" + recipe.getId());
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public MedicineDao getmDao() {
        //nesnemizin null gelme olasiligini ortadan kaldirmak amaciyla kontrol gerceklestiriyoruz
        if(this.mDao == null)
            this.mDao = new MedicineDao();
        return mDao;
    }

    public SickDao getsDao() {
        if(this.sDao == null)
            this.sDao = new SickDao();
        return sDao;
    }

    public DoctorDao getdDao() {
        if(this.dDao == null)
            this.dDao = new DoctorDao();
        return dDao;
    }
    
}
