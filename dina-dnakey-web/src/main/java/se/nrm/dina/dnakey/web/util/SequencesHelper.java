/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.dnakey.web.util;

import java.io.BufferedReader; 
import java.io.Serializable;
import java.io.StringReader; 
import java.sql.Timestamp;
import java.util.ArrayList; 
import java.util.Arrays;
import java.util.List; 
import java.util.StringTokenizer; 
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author idali
 */
@Slf4j
public class SequencesHelper implements Serializable  {
    
    private Timestamp timestamp;
    private final String REGEX = "[\r\n]+";
    private final String HEADER_TAG = ">";
    private final String NEW_LINE = "\n";
    
    public SequencesHelper() {
        
    }
    
    private int getMaxCount(String... strings) {
        return strings.length > 100 ? 99 : strings.length;
    }

    private List<String> buildSequenceList(String sequence) {
        String[] strings = sequence.split(REGEX);
        List<String> sequences = new ArrayList<>(); 
        Arrays.stream(strings, 0, getMaxCount(strings))
                    .filter(string -> !isEmpty(string))
                    .forEach(string -> {
                        sequences.add(addSequenceHeader(string.trim()));
                    }); 
        return sequences;
    }
    
    public List<String> prepareSequenceList(String sequence) {
        
        List<String> sequences = new ArrayList<>(); 
        StringTokenizer tokens = new StringTokenizer(sequence, HEADER_TAG, true); 
        if (tokens.countTokens() <= 1) {                                            // sequences without header
            return buildSequenceList(sequence);   
        }
        
        // multiple sequences
        StringBuilder sb = new StringBuilder();
        List<String> strings;
        while (tokens.hasMoreTokens()) {  
            String string = tokens.nextToken();
            if(string.contains(HEADER_TAG)) {
                sb.append(string);
                sb.append(tokens.nextToken());  
                strings = buildStringList(sb.toString());
           
                sb = new StringBuilder();
                for (String s : strings) {
                    
                    if (!StringUtils.isEmpty(s)) { 
                        if (!s.contains(HEADER_TAG)) {                         // if sequence has no head 
                            s = addSequenceHeader(s);
                        } 
                        sequences.add(s);
                    }
                }
            } else {
                sequences = buildSequenceList(string); 
            }
        }
        return sequences; 
    }
     

    private String addSequenceHeader(String string) {
        log.info("addSequenceHeader");
        timestamp = new Timestamp(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder(HEADER_TAG);
        sb.append(timestamp);
        sb.append(NEW_LINE);
        sb.append(string);
        return sb.toString().trim();
    }

    public List<String> buildStringList(String string) {
        log.info("buildStringList : {}", string);

        List<String> strings = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        new BufferedReader(new StringReader(string))
                .lines().forEach(s -> {
                    if (!isEmpty(s)) {
                        sb.append(s);
                        if (s.contains(HEADER_TAG)) {
                            sb.append(NEW_LINE);
                        } 
                    } else {
                        if (!isEmpty(sb.toString())) {
                            strings.add(sb.toString());
                        } 
                        sb.setLength(0);
                    }
                });
        if(!isEmpty(sb.toString().trim())) {
            strings.add(sb.toString());
        } 
        return strings;
    }

    private static boolean isEmpty(String line) {

        int start = 0;
        while (start < line.length()) {
            if (CommonString.getInstance().getWhiteSpaceChars().indexOf(line.charAt(start)) == -1) {
                break;
            }
            start++;
        }
        line = line.substring(start);  
        return line.length() == 0;
    } 
}
