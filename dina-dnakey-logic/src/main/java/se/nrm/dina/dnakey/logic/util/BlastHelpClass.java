/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.dnakey.logic.util;

import se.nrm.dina.dnakey.logic.config.ConfigProperties;

/**
 *
 * @author idali
 */
public class BlastHelpClass {
    
    private static BlastHelpClass instance = null;
  
    public static synchronized BlastHelpClass getInstance() {
        if (instance == null) {
            instance = new BlastHelpClass();
        }
        return instance;
    }
    
    public String getBlastDbInfoCommand(ConfigProperties config, String dbName) {
        StringBuilder sb = new StringBuilder();
        sb.append(config.getDbinfoPath());
        sb.append(" -db ");
        sb.append(config.getDbPath());
        sb.append(dbName);
        sb.append(" -info");
        return sb.toString().trim();
    }
}
