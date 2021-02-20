package com.upgrad.quora.api.modal;

public class SigninResponse {

    private String id;
    private String message;

    public SigninResponse id(String id){
        this.id = id;
        return this;
    }

    public SigninResponse message(String message){
        this.message = message;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
