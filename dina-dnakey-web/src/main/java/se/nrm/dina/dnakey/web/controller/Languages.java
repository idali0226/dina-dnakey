/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.dnakey.web.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.dnakey.web.util.CommonString;

/**
 *
 * @author idali
 */
@SessionScoped
@Named("languages")
@Slf4j
public class Languages implements Serializable { 
   
    private String locale = CommonString.getInstance().getSv();  

    public Languages() {
    } 
 
    public String getLocale() {
        return locale;
    }

//    public void setLocale(String locale) {
//        this.locale = locale;
//    }
      
    public void changelanguage(String locale) { 
        log.info("changelanguage - locale: {}", locale);
         
        this.locale = locale;
    }
  
    public String getLanguage() {
        return locale.equals(CommonString.getInstance().getSv()) ? CommonString.getInstance().getEnglish() : CommonString.getInstance().getSwedish();
    }
      
    public boolean isIsSwedish() {
        return locale.equals(CommonString.getInstance().getSv());
    }
}
