/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.dnakey.web.util;

/**
 *
 * @author idali
 */
public class CommonString {
    
    private final String SV = "sv";
    private final String EN = "en";
    private final String SWEDISH = "Svenska";
    private final String ENGLISH = "English";
    
    private static CommonString instance = null;
  
    public static synchronized CommonString getInstance() {
        if (instance == null) {
            instance = new CommonString();
        }
        return instance;
    }
    
    public String getSv() {
        return SV;
    }
     
    public String getEn() {
        return EN;
    }
    
    public String getSwedish() {
        return SWEDISH;
    }
    
    public String getEnglish() {
        return ENGLISH;
    }
}
