/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PwDAO;
import entity.pw;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author gurkangltekin
 */
@Named
@SessionScoped
public class PwController implements Serializable {
    private List<pw> pwList;
    private PwDAO pwDao;

    public PwController() {
        this.pwList = new ArrayList();
        this.pwDao = new PwDAO();
    }

    public List<pw> getPwList() {
        this.pwList = this.getPwDao().getPw();
        return pwList;
    }

    public void setPwList(List<pw> pwList) {
        this.pwList = pwList;
    }

    public PwDAO getPwDao() {
        return pwDao;
    }

    public void setPwDao(PwDAO pwDao) {
        this.pwDao = pwDao;
    }
    
    
    
}
