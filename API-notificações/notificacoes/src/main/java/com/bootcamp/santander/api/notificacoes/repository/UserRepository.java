package com.bootcamp.santander.api.notificacoes.repository;
import com.bootcamp.santander.api.notificacoes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}