package com.zavgorodniy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
public class LoginController {

    @RequestMapping(value = "/login")
        public String logIn() throws SQLException {

        return "login";
    }

}
