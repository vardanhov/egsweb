package com.egswebapp.egsweb.dto.response;

public class ErrorResponse {

    private String fields;

    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(String fields, String message) {
        this.fields = fields;
        this.message = message;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
