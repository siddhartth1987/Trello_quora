package com.upgrad.quora.api.modal;

import org.springframework.http.ResponseEntity;

public class SignupUserResponse {

    private String id;
    private String status;

    public SignupUserResponse id(String uuid) {
        this.id = uuid;
        return this;
    }

    public SignupUserResponse status(String status) {
        this.status = status;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
