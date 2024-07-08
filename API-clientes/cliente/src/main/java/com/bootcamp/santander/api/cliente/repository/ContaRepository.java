package com.bootcamp.santander.api.cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.santander.api.cliente.model.ContaModel;

public interface ContaRepository extends JpaRepository<ContaModel, Integer> {
    List<ContaModel> findContaByUserId(Integer idUser);

    List<ContaModel> findContaPasswordByUserId(Integer idUser);
}
