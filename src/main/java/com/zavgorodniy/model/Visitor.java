package com.zavgorodniy.model;

import org.springframework.stereotype.Component;

/**
 * Created by ADMIN on 03.08.2015.
 */
public class Visitor {

    private String name;

    private String lastName;

    private boolean checkAdmin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isCheckAdmin() {
        return checkAdmin;
    }

    public void setCheckAdmin(boolean checkAdmin) {
        this.checkAdmin = checkAdmin;
    }
}
