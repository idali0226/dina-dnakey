/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.dnakey.web.controller;

import java.io.Serializable; 
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent; 
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j; 
import se.nrm.dina.dnakey.logic.BlastDbInfo;
import se.nrm.dina.dnakey.logic.Blaster;
import se.nrm.dina.dnakey.logic.vo.BlasterDbVo;

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
    
    private String database = DEFAULT_BLASTDB;
    private String nrmTotalSequence;
    private String boldTotalSequence;
    private String genBankTotalSequence;
     
    @Inject
    @BlastDbInfo
    private BlasterDbVo blasterDbVo;
    
    public BlastBean() {
        
    }
   
    @PostConstruct
    public void init() {
        log.info("init");
         
        nrmTotalSequence = blasterDbVo.getTotalNrmSequences(); 
        boldTotalSequence = blasterDbVo.getTotalBoldSequences();
        genBankTotalSequence = blasterDbVo.getTotalGenbankSequences();
    }
   
    
    public void submit() {
        log.info("submit");
    }
    
    public void databaseChanged(final AjaxBehaviorEvent event) {
        log.info("databaseChanged : {}", database);  
    }
    
    public void uploadfile() {
        log.info("uploadfile"); 
    }
    
    public String getSequence() {
        log.info("getSequence: {}", sequence);
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
}
