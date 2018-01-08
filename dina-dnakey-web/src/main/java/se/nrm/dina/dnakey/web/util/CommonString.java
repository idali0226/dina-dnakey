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
public class CommonString {
    
    private final String SV = "sv";
    private final String EN = "en";
    private final String SWEDISH = "Svenska";
    private final String ENGLISH = "English";
    
    private final String BLAST_FAILED_EN = "Blast failed";
    private final String BLAST_FAILED_SV = "Blast misslyckades";
    
    private final String INPUT_SEQUENCES_EN = "Input sequence(s) in FASTA format or upload file(s) in FASTA format.";
    private final String INPUT_SEQUENCES_SV = "Mata in sekvens(er) i FASTA - format eller ladda upp fil(er) i FASTA - format.";
    
    
    
    
    
    
    
    private final String WHITE_SPACE_CHARS = "[ \t\r\n\f]";
    
    private static CommonString instance = null;
  
    public static synchronized CommonString getInstance() {
        if (instance == null) {
            instance = new CommonString();
        }
        return instance;
    }
    
    public String getWhiteSpaceChars() {
        return WHITE_SPACE_CHARS;
    }
    
    public String getInputSequencesText(boolean isSv) {
        return isSv ? INPUT_SEQUENCES_SV : INPUT_SEQUENCES_EN;
    }
    
    public String getBlastFailedText(boolean isSv) {
        return isSv ? BLAST_FAILED_SV : BLAST_FAILED_EN;
    }
    
    public String getSv() {
        return SV;
    }
     
    public String getEn() {
        return EN;
    }
    
    public String getSwedish() {
        return SWEDISH;
    }
    
    public String getEnglish() {
        return ENGLISH;
    }
}
