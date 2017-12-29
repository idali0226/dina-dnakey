/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.dnakey.logic.geomap;

import java.io.IOException;
import java.io.Serializable; 
import java.nio.file.Files;
import java.nio.file.Paths;  
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream; 
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.dnakey.logic.config.ConfigProperties; 
import se.nrm.dina.dnakey.logic.vo.GeoData;

/**
 *
 * @author idali
 */ 
@Slf4j
public class GeoMapDataSource implements Serializable {
      
    private final static String SEPARATOR = "\t";
  
    @Inject
    private ConfigProperties config;

    private static List<GeoData> GEO_DATA;

    public GeoMapDataSource() {

    }

    /**
     * init method runs when the application server starts up and
     * user-management application start to deploy
     */
    @PostConstruct
    public void init() {
        log.info("init");

        String geoDataFilePath = config.getGeoDataFilePath(); 
        
        try (Stream<String> stream = Files.lines(Paths.get(geoDataFilePath))) { 
            GEO_DATA = stream.map(l -> mapperToGeoMapData(l))
                                .collect(Collectors.toList());    
        } catch (IOException e) {
        } 
    }

    private static GeoData mapperToGeoMapData(String value) {
        String[] geo = value.split(SEPARATOR);
        return new GeoData(Double.parseDouble(geo[2]), Double.parseDouble(geo[1]), Integer.parseInt(geo[0]));
    }
    
    public List<GeoData> getGeoMapData() { 
        return GEO_DATA;
    }
 
    
}
