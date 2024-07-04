package com.bootcamp.santander.api.cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.santander.api.cliente.model.ClienteModel;
import com.bootcamp.santander.api.cliente.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clientRepository;

    @GetMapping("/")
    public List<ClienteModel> index(ClienteModel clienteModel) {
        return this.clientRepository.findAll();
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/{id}")
    public ResponseEntity getClientById(@PathVariable Integer id) {
        var client = this.clientRepository.findById(id);
        System.out.println("Cliente " + client);
        if (client.isEmpty()) {
            System.out.println("BAD");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/")
    public ResponseEntity store(@RequestBody ClienteModel clientModel) {
        var createClient = this.clientRepository.save(clientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createClient);
    }
}
