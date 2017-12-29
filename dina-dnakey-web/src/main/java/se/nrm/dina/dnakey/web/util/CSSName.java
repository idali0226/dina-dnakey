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
public class CSSName {
    
    private final String CURRENT_TAB = "current";
    private final String DEFAULT_TAB = "";
    
    private static CSSName instance = null;
    
    public static synchronized CSSName getInstance() {
        if (instance == null) {
            instance = new CSSName();
        }
        return instance;
    } 
    
    public String getCurrentTab() {
        return CURRENT_TAB;
    }
    
    public String getDefaultTab() {
        return DEFAULT_TAB;
    }
}
