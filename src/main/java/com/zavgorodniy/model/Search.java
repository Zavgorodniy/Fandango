package com.zavgorodniy.model;

import org.springframework.stereotype.Component;

/**
 * Created by ADMIN on 28.07.2015.
 */
@Component
public class Search {

    private String req;

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }
}
