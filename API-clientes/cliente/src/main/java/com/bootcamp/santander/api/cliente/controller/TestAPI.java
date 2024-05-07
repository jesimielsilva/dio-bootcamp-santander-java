package com.bootcamp.santander.api.cliente.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAPI {

   @GetMapping("/api/teste")
   public String teste(){
       return "Hello Mundo";
   }
}
