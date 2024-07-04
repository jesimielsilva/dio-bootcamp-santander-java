package com.bootcamp.santander.api.cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.santander.api.cliente.model.UserModel;
import com.bootcamp.santander.api.cliente.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @SuppressWarnings("rawtypes")
    @GetMapping("/")
    public ResponseEntity index(UserModel userModel, HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var user = this.userService.findUserById((Integer) idUser);
        System.out.println(user);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este usuario não está cadastrado!");
        }
        return ResponseEntity.status(200).body(user);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/cadastrar")
    public ResponseEntity store(@RequestBody UserModel userModel) {
        try {
            UserModel userCreated = userService.store(userModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um erro!" + e);
        }
    }

}
