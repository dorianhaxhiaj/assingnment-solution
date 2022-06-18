package com.enovation.assingmentsolution.domains;

import org.springframework.http.HttpStatus;

public class DeleteResponse {
    private Boolean response;
    private String message;
    private HttpStatus httpStatus;

    public DeleteResponse(Boolean response, String message, HttpStatus httpStatus) {
        this.response = response;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
