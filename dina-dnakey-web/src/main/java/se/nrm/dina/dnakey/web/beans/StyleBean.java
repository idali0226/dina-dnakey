/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.dnakey.web.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.dnakey.web.util.CSSName;

/**
 *
 * @author idali
 */
@SessionScoped
@Named
@Slf4j
public class StyleBean implements Serializable {
    
    private String tab1 = CSSName.getInstance().getCurrentTab();
    private String tab2;
    private String tab3;
    private String tab4;
    
    public StyleBean() {

    }

    public String getTab1Style() {
        return tab1;
    }
 
    public String getTab2Style() {
        return tab2;
    }
 
    public String getTab3Style() {
        return tab3;
    }
 
    public String getTab4Style() {
        return tab4;
    }
  
    public void resetTabStyle(int tabIndex) {
         
        tab1 = CSSName.getInstance().getDefaultTab();
        tab2 = CSSName.getInstance().getDefaultTab();
        tab3 = CSSName.getInstance().getDefaultTab();
        tab4 = CSSName.getInstance().getDefaultTab();
        
        switch (tabIndex) {
            case 0:
                tab1 = CSSName.getInstance().getCurrentTab();
                break;
            case 1:
                tab2 = CSSName.getInstance().getCurrentTab();
                break;
            case 2:
                tab3 = CSSName.getInstance().getCurrentTab();
                break;
            case 3:
                tab4 = CSSName.getInstance().getCurrentTab();
                break;
            case 4: break;
            default:
                tab1 = CSSName.getInstance().getCurrentTab();
                break;
        }
    }
}
