/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.dnakey.web.logic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.biojava3.core.exceptions.CompoundNotFoundError;
import org.biojava3.core.sequence.io.FastaReaderHelper;

/**
 *
 * @author idali
 */
@Slf4j
public class SequenceValidation implements Serializable {
    
    private String errorMsg;
    private List<String> errorMsgs = new ArrayList<>();
    
    public SequenceValidation() {
        
    }
    
    public boolean validate(String sequence) {
        log.info("sequence : {}", sequence);
        InputStream stream = null;
        try {
            sequence = StringUtils.replaceChars(sequence, "UuRrYySsWwKkMmBbDdHhVv", "n");
            stream = new ByteArrayInputStream(sequence.getBytes("UTF-8"));
            FastaReaderHelper.readFastaDNASequence(stream);  
            return true;
        } catch (Exception ex) { 
            return false;
        } finally {
            try {
                if(stream != null) {
                    stream.close();
                } 
            } catch (IOException ex) {
                log.error(ex.getMessage());
            }
        } 
    }
    
    
    public boolean validate(List<String> seqs) {
        
        log.info("validate : {}", seqs);
        if (seqs != null && !seqs.isEmpty()) {
            if (seqs.size() == 1) {
                return isSequenceValid(seqs.get(0));
            } else {
                return isMultipleSequencesValid(seqs);
            }
        }
        return false;
    }
    
    /**
     * Validates sequence
     * 
     * @param singleSeq - a single fasta sequence
     * 
     * @return true if it is a valid fasta sequence
     */
    private boolean isSequenceValid(String singleSeq) {
         
        errorMsg = null;
        if (singleSeq == null) {
            errorMsg = "Invalid fasta sequence!";   
        } else {
            try {
                validate(singleSeq.trim()); 
            } catch (CompoundNotFoundError ex) {
                errorMsg = ex.getMessage(); 
            } 
        } 
        return errorMsg == null;
    }
    
    /**
     * Validates multiple fasta sequences 
     *  
     * @return true is all the fasta sequences are valid
     */
    private boolean isMultipleSequencesValid(List<String> seqs) {
        
        errorMsgs = new ArrayList<>();

        StringBuilder sb;
        
        boolean isValid = true; 
        int count = 0;  
        for (String seq : seqs) {
            sb = new StringBuilder();
            count++;  
            if (!isSequenceValid(seq)) {
                sb.append("The sequence number ");
                sb.append(count);
                sb.append(" is invalid. ");
                sb.append(errorMsg);
                sb.append("\n");
                isValid = false;
                errorMsgs.add(sb.toString());
            }  
        } 
        return isValid;
    }
    
    public List<String> getErrorMsgs() {
        return errorMsgs;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
    
}
