package com.sthi.re.dto.response;

public class OtpResponse {

    private String status;
    private String message;

    public OtpResponse(String status, String message) {
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
