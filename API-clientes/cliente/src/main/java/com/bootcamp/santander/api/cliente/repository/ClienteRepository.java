package com.bootcamp.santander.api.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.santander.api.cliente.model.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {

}
