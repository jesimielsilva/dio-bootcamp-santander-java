package com.bootcamp.santander.api.cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.santander.api.cliente.model.ContaModel;
import com.bootcamp.santander.api.cliente.service.ContaService;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @SuppressWarnings("rawtypes")
    @PostMapping("/")
    public ResponseEntity index(ContaModel contaModel, HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        System.out.println("Passou no controller");
        var userAccountList = this.contaService.findContaByUserId((Integer) idUser);
        if (userAccountList == null || userAccountList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este usuário não tem nenhuma conta cadastrada!");
        }
        return ResponseEntity.status(200).body(userAccountList);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/cadastrar")
    public ResponseEntity store(@RequestBody ContaModel contaModel, HttpServletRequest request) {
        try {
            var passwordHashed = BCrypt.withDefaults().hashToString(12, contaModel.getPassword().toCharArray());
            contaModel.setPassword(passwordHashed);

            var idUser = request.getAttribute("idUser");
            contaModel.setUserById((Integer) idUser);
            var createAccount = this.contaService.store(contaModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um erro: " + e.getMessage());
        }
    }
}
