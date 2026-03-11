package com.sthi.re.dto.response;

public class CreateLeadResponse {

    private String status;
    private String message;

    public CreateLeadResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
