package com.sthi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AstroData {
    private String lagna;
    private String sunSign;
    private String moonSign;
    private String nakshatra;
    private Integer pada;
    private Map<String, Double> planetPositions;
    private List<String> doshas;
    private String sunrise;
    private String sunset;
    private String tithi;
    private String yoga;
    private String karana;
    private String sunriseNextDay;
    private String ayanamsa;
    private String panchangDay;
}