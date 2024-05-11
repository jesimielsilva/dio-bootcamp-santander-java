package com.bootcamp.santander.api.formwork.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteApp {

    @GetMapping("/api/teste")
    public String teste(){
        return "hello world";
    }

}
