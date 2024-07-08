package com.bootcamp.santander.api.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.santander.api.cliente.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findUserByCpfAndName(String cpf, String name);

    UserModel findUserById(Integer idUser);

    UserModel findUserByCpf(String cpf);
}
