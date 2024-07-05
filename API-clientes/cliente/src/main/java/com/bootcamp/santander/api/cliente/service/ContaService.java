package com.bootcamp.santander.api.cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.santander.api.cliente.model.ContaModel;
import com.bootcamp.santander.api.cliente.repository.ContaRepository;
import com.bootcamp.santander.api.cliente.repository.UserRepository;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ContaModel> findContaByUserId(Integer idUser) {
        var listCounts = this.contaRepository.findContaByUserId(idUser);
        return listCounts;
    }

    public ContaModel store(ContaModel contaModel) {
        try {
            var user = this.userRepository.findUserById(contaModel.getUser().getId());
            contaModel.setUser(user);
            return this.contaRepository.save(contaModel);
        } catch (Exception err) {
            System.out.println("Err: " + err);
            throw err;
        }
    }

}
