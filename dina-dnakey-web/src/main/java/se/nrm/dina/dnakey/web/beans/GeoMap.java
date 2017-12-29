/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.dnakey.web.beans;

import java.io.Serializable; 
import java.util.List;
import javax.annotation.PostConstruct; 
import javax.enterprise.context.SessionScoped;  
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j; 
import se.nrm.dina.dnakey.logic.geomap.GeoMapDataSource;
import se.nrm.dina.dnakey.logic.vo.GeoData;
//import se.nrm.dina.dnakey.logic.geomap.GeoMapDataSource;
//import se.nrm.dina.dnakey.logic.vo.GeoData;

/**
 *
 * @author idali
 */
@Named("map")
@SessionScoped 
@Slf4j
public class GeoMap implements Serializable {
    
    private List<GeoData> geoMap;

    @Inject
    private GeoMapDataSource geoData;
    
    public GeoMap() {
        
    }

    @PostConstruct
    public void init() { 
        log.info("GeoMap - init");
        if(geoMap == null) {
            geoMap = geoData.getGeoMapData(); 
        } 
    }
 
    public List<GeoData> getGeoMap() { 
//        log.info("getGeoMap: {}", geoMap.size()); 
        
        return geoMap;
    }   
}
