package com.sthi.re.dto.response;

public class LookupDto {

    private Long id;
    private String label;

    public LookupDto(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}