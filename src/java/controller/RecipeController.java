package controller;

import entity.*;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author gurkangltekin
 * 
 * bu sinifimiz, recete nesnemizin ve veritabanindaki recete verilerini arayuzde
 * goruleyebilmemiz icin nesne ve arayuz arasinda haberlesme ve kontrol gorevi goruyor.
 */
@Named
@SessionScoped
public class RecipeController extends Controller implements Serializable {
    
    @Override
    public void clearForm(){
        this.setRecipe(new recipe());
    }
    
    @Override
    public void update(){
        this.getRecipeDao().update(this.getRecipe(),0);
        this.setRecipe(new recipe());
    }
    
    @Override
    public void updateForm(Object obj){
        recipe recipe = (recipe)obj;
        this.setRecipe(recipe);
    }
    
    @Override
    public void deleteConfirm(Object obj){
        recipe recipe = (recipe)obj;
        this.setRecipe(recipe);
    }
    
    @Override
    public void delete(){
        this.getRecipeDao().delete(this.getRecipe());
        this.setRecipe(new recipe());
    }
    
    @Override
    public void create(){
        this.getRecipeDao().insert(this.getRecipe(),0);
        this.setRecipe(new recipe());
    }
}
