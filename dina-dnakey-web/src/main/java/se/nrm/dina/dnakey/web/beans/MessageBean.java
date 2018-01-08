/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.dnakey.web.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author idali
 */
@SessionScoped 
@Named("message")
@Slf4j
public class MessageBean implements Serializable {
    
    public MessageBean() {
        
    }
    
    public void addError(String errorTitle, String errorMsg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorTitle, errorMsg));
    }
     
    public void addInfo(String msgTitle, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msgTitle, msg));
    }  
    
    public void addWarning(String warningTitle, String warningMsg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, warningTitle, warningMsg));
    } 
}
