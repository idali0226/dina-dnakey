/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.dnakey.web.controller;

import java.io.Serializable; 
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent; 
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j; 
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.TabChangeEvent;
import se.nrm.dina.dnakey.logic.BlastDbInfo; 
import se.nrm.dina.dnakey.logic.vo.BlasterDbVo;
import se.nrm.dina.dnakey.web.beans.MessageBean;
import se.nrm.dina.dnakey.web.logic.SequenceValidation;
import se.nrm.dina.dnakey.web.util.CommonString;
import se.nrm.dina.dnakey.web.util.SequencesHelper;

/**
 *
 * @author idali
 */
@SessionScoped 
@Named("blast")
@Slf4j
public class BlastBean implements Serializable {
    
    private final String URL_ENCODE = "param=dnakey&catalogNumber="; 
    private final String DEFAULT_BLASTDB = "nrm";
    private final String NCBI_BLAST_POTAL_URL = "http://www.ncbi.nlm.nih.gov/blast/Blast.cgi?PROGRAM=blastn&PAGE_TYPE=BlastSearch&LINK_LOC=blasthome";
    private final String NCBI_BLAST_DOCUMENT = "http://blast.ncbi.nlm.nih.gov/Blast.cgi?CMD=Web&PAGE_TYPE=BlastDocs";
    private final String NCBI_BLAST_PLUS_DOCUMENT = "http://blast.ncbi.nlm.nih.gov/Blast.cgi?CMD=Web&PAGE_TYPE=BlastDocs&DOC_TYPE=Download";
    private final String GENBANK_RID_URL = "http://www.ncbi.nlm.nih.gov/blast/Blast.cgi?CMD=Get&RID=";
    
    private String sequence;
    private List<String> sequences;
 
    private String database = "nrm";
    private String nrmTotalSequence;
    private String boldTotalSequence;
    private String genBankTotalSequence;
    
    private String testSequences;
    private int numOfTestSeqs = 0;  
     
    private int activeTab = 1;
    private boolean isSwedish;
    
    @Inject
    @BlastDbInfo
    private BlasterDbVo blasterDbVo;
    
    @Inject
    private MessageBean message;
    
    @Inject
    private Languages languages;
    
    @Inject
    private SequenceValidation validation;
    
    @Inject
    private SequencesHelper helper;
    
    public BlastBean() {
        database = DEFAULT_BLASTDB; 
        isSwedish = true;
    }
   
    @PostConstruct
    public void init() {
        log.info("init");
         
        database = DEFAULT_BLASTDB;
        nrmTotalSequence = blasterDbVo.getTotalNrmSequences(); 
        boldTotalSequence = blasterDbVo.getTotalBoldSequences();
        genBankTotalSequence = blasterDbVo.getTotalGenbankSequences();
        isSwedish = true;
    }
   
    
    public void submit() {
        log.info("submit : database: {} -- tab: {}", database, activeTab);
        
        isSwedish = languages.isIsSwedish();
        if(activeTab == 1) {
            blastSequences();
        }
    }
    
    private void blastSequences() {
        log.info("blastSequences " );
         
        sequences = new ArrayList<>();
        if (StringUtils.isEmpty(sequence)) {
            message.addError(CommonString.getInstance().getBlastFailedText(isSwedish), CommonString.getInstance().getInputSequencesText(isSwedish));
        } else {
            sequences = helper.prepareSequenceList(sequence);
            run();
        }
    }

    private void run() {
        log.info("sequences : {}", sequences);
        boolean isSequenceValid = validation.validate(sequences);
        log.info("isvalid: {}", isSequenceValid);
//        if (validation.validate(sequences)) {
//            blast();
//            section = 11;
//            setTopMenuStyle(4);
//        } else {
//            if (sequences != null && sequences.size() > 1) {
//                createErrorMsgs(ConstantString.getInstance().getText("validationfailed_" + languages.getLocale()), validation.getErrorMsgs());
//            }
//            addError(ConstantString.getInstance().getText("validationfailed_" + languages.getLocale()), validation.getErrorMsg());
//        }
    }

    public void databaseChanged(final AjaxBehaviorEvent event) {
        log.info("databaseChanged : {}", database);  
    }
    
    public void onTabChange(TabChangeEvent event) {
        log.info("onTabChange : {}", event.getTab().getId()); 
        
        String tab = event.getTab().getId();
        switch (tab) {
            case "tab3":
//                if(testSequences == null || testSequences.isEmpty()) {
//                    numOfTestSeqs = 0;
//                } 
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
    
    
    
    public void uploadfile() {
        log.info("uploadfile"); 
    }
    
    public String getSequence() { 
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
    
 
     
    public String getNrmTotalSequence() {
        return nrmTotalSequence;
    }
 
    public String getBoldTotalSequence() {
        return boldTotalSequence;
    }

    public String getGenBankTotalSequence() {
        return genBankTotalSequence;
    }
    
    public String getUrlEncode() {
        return URL_ENCODE;
    }
     
    public String getNcbiBlastportalUrl() {
        return NCBI_BLAST_POTAL_URL; 
    }
    
    public String getNcbiBlastDocument() {
        return NCBI_BLAST_DOCUMENT;
    }
    
    public String getNcbiBlastPlusDocument() {
        return NCBI_BLAST_PLUS_DOCUMENT;
    }
    
    public String getGenbankRIDUrl() {
        return GENBANK_RID_URL;
    }

    public int getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(int activeTab) {
        this.activeTab = activeTab;
    }

    public String getTestSequences() {
        return testSequences;
    }

    public void setTestSequences(String testSequences) {
        this.testSequences = testSequences;
    }

    public int getNumOfTestSeqs() {
        return numOfTestSeqs;
    }

    public void setNumOfTestSeqs(int numOfTestSeqs) {
        this.numOfTestSeqs = numOfTestSeqs;
    }
    
    
}
