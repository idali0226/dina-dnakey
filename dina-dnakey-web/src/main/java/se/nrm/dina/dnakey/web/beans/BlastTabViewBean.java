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
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author idali
 */
@SessionScoped
@Named("tabview")
@Slf4j
public class BlastTabViewBean implements Serializable {
    
    private int activeTab = 1;
    private int numOfTestSeqs;
    private String testSequences;

    public BlastTabViewBean() {

    }

    public void onTabChange(TabChangeEvent event) {
        log.info("onTabChange : {}", event.getTab().getId());

        String tab = event.getTab().getId();
        switch (tab) {
            case "tab3": 
                activeTab = 3;
                break;
            case "tab1":
                activeTab = 1;
                break;
            case "tab2":
                activeTab = 2;
                break;
        }
    }
    
    public int getActiveTab() {
        return activeTab;
    }

    public int getNumOfTestSeqs() {
        return numOfTestSeqs;
    }

    public void setNumOfTestSeqs(int numOfTestSeqs) {
        this.numOfTestSeqs = numOfTestSeqs;
    }

    public String getTestSequences() {
        return testSequences;
    }

    public void setTestSequences(String testSequences) {
        this.testSequences = testSequences;
    }

    
}
