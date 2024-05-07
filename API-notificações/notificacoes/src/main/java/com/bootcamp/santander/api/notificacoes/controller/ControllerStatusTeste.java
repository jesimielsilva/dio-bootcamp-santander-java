package com.bootcamp.santander.api.notificacoes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerStatusTeste {

    //teste para verificar se a aplicação esta rodando obs: jpa comentada
    @GetMapping("/api/teste")
    public String verificar(){
        return "Hello, World";
    }

}
