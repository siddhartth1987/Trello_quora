package com.upgrad.quora.api.modal;

public class DeleteUserResponse {

    private String uuid;
    private String message;

    public DeleteUserResponse(String uuid, String message) {
        this.uuid = uuid;
        this.message = message;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
