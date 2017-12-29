/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.dnakey.logic.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import se.nrm.dina.dnakey.logic.Blaster;
import se.nrm.dina.dnakey.logic.config.ConfigProperties;
import se.nrm.dina.dnakey.logic.util.BlastHelpClass;

/**
 *
 * @author idali
 */
@Slf4j
public class BlasterImpl implements Blaster, Serializable {
    
    @Inject
    private ConfigProperties config;

    @Override
    public String getBlastDbInfo(String db) {
        log.info("getBlastDbInfo : {}", db);
        
        InputStream is = null;
        BufferedReader in = null;
         
        Process process = null; 
        try {
            process = Runtime.getRuntime().exec(BlastHelpClass.getInstance().getBlastDbInfoCommand(config, db));
            is = process.getInputStream();
            in = new BufferedReader(new InputStreamReader(is));
             
            String line;
            while ((line = in.readLine()) != null) { 
                if (line.contains("sequences")) { 
                    return StringUtils.substringBefore(line, " sequences").trim();
                }
            } 
        } catch (IOException ex) {
            log.error(ex.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if(in != null) {
                    in.close();
                }
                if(process != null && process.isAlive()) {
                    process.destroy();
                }
            } catch (IOException ex) {
                log.info(ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public String remoteGenbankBlast(String fastSequence) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
