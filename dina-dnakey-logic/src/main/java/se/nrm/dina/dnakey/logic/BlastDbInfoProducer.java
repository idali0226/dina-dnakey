/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.dnakey.logic;

import java.io.BufferedReader;
import java.io.IOException; 
import java.io.InputStreamReader; 
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import se.nrm.dina.dnakey.logic.config.ConfigProperties;
import se.nrm.dina.dnakey.logic.util.BlastHelpClass;
import se.nrm.dina.dnakey.logic.vo.BlasterDbVo;

/**
 *
 * @author idali
 */
@ApplicationScoped
@Startup 
@Slf4j
public class BlastDbInfoProducer {
    
    private BlasterDbVo blastDbVo;
    private final String NRM_DB = "nrm";
    private final String BOLD_DB = "bold";
    private final String GENBANK_DB = "genbank";
    
    @Inject
    private ConfigProperties config;
    
    
    public BlastDbInfoProducer() {
        
    }
    
    @PostConstruct
    public void init() {
        log.info("init");
        
        String nrmCount = getTotal(NRM_DB);
        String boldCount = getTotal(BOLD_DB);
        String genbankCount = getTotal(GENBANK_DB);
        
        log.info("total : {} -- {}", nrmCount, boldCount + " -- " + genbankCount);
        blastDbVo = new BlasterDbVo(nrmCount, boldCount, genbankCount);
    } 

    /**
     * 
     * Produce CDI BlasterDbVo
     * 
     * @return BlasterDbVo
     */
    @Produces
    @BlastDbInfo
    public BlasterDbVo getBlastDbInfo() {
        return blastDbVo;
    }

    private String getTotal(String db) {

        Process process = null;
        Runtime runtime = Runtime.getRuntime();
        try {
            process = runtime.exec(BlastHelpClass.getInstance().getBlastDbInfoCommand(config, db));
            try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));) {
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.contains("sequences")) {
                        return StringUtils.substringBefore(line, " sequences").trim();
                    }
                }
            }
        } catch (IOException ex) {
            log.error(ex.getMessage());
        } finally {
            if (process != null && process.isAlive()) {
                process.destroy();
            }
        }
        return null;


            
            
             
        
        
        
//        InputStream is = null;
//        BufferedReader in = null;
//         
//        Process process = null; 
//        try {
//            process = Runtime.getRuntime().exec(BlastHelpClass.getInstance().getBlastDbInfoCommand(config, db));
//            is = process.getInputStream();
//            in = new BufferedReader(new InputStreamReader(is));
//             
//            String line;
//            while ((line = in.readLine()) != null) { 
//                if (line.contains("sequences")) { 
//                    return StringUtils.substringBefore(line, " sequences").trim();
//                }
//            } 
//        } catch (IOException ex) {
//            log.error(ex.getMessage());
//        } finally {
//            try {
//                if (is != null) {
//                    is.close();
//                }
//                if(in != null) {
//                    in.close();
//                }
//                if(process != null && process.isAlive()) {
//                    process.destroy();
//                }
//            } catch (IOException ex) {
//                log.info(ex.getMessage());
//            }
//        } 
//        return null;
    }
}
