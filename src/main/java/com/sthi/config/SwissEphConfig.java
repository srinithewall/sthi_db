package com.sthi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import swisseph.SweConst;
import swisseph.SwissEph;
import java.io.File;

@Configuration
public class SwissEphConfig {
    
    @Bean
    public SwissEph swissEph() {
        SwissEph swissEph = new SwissEph();
        
        // Set ephemeris file path
        String ephePath = System.getProperty("user.dir") + "/ephemeris";
        File epheDir = new File(ephePath);
        if (!epheDir.exists()) {
            epheDir.mkdirs();
        }
        swissEph.swe_set_ephe_path(ephePath);
        
        // Set Lahiri Ayanamsa (SE_SIDM_LAHIRI = 1)
        swissEph.swe_set_sid_mode(SweConst.SE_SIDM_LAHIRI, 0, 0);
        
        return swissEph;
    }
}