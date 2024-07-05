package com.bootcamp.santander.api.cliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bootcamp.santander.api.cliente.model.UserModel;
import com.bootcamp.santander.api.cliente.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel findUserByCpfAndName(String cpf, String name) {
        return this.userRepository.findUserByCpfAndName(cpf, name);
    }

    public UserModel findUserById(Integer idUser) {
        return this.userRepository.findUserById(idUser);
    }

    public UserModel findUserByCpf(String cpf) {
        return this.userRepository.findUserByCpf(cpf);
    }

    public UserModel store(@RequestBody UserModel userModel) {
        try {
            var nameLowerCase = userModel.getName().toLowerCase();
            userModel.setName(nameLowerCase);
            return this.userRepository.save(userModel);
        } catch (Exception err) {
            System.out.println("Error: " + err);
            throw err;
        }
    }
}
