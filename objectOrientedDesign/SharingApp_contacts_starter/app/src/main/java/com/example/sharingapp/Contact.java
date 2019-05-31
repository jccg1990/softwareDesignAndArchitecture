package com.example.sharingapp;

import java.util.UUID;

public class Contact {

    private String username;
    private String email;
    private String id;

    public Contact(String username, String email, String id){
        this.username = username;
        this.email = email;

        if (id == null) {
            setId();
        } else {
            updateId(id);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername() {
        //no params as per the UML, so it does nothing
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        //no params as per the UML, so it does nothing
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
    }

    public void updateId(String id){
        this.id = id;
    }
}
