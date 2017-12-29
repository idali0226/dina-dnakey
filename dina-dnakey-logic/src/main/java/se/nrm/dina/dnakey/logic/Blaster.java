/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.dnakey.logic;

/**
 *
 * @author idali
 */
public interface Blaster {
    /**
     * Get blast db data
     * @param db
     * @return String
     */
    public String getBlastDbInfo(String db);
     
    /**
     * Blast from genbank
     * @param fastSequence
     * @return String
     */
    public String remoteGenbankBlast(String fastSequence);
}
