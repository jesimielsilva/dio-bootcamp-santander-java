package com.bootcamp.santander.api.notificacoes.service;

import com.bootcamp.santander.api.notificacoes.entity.User;
import com.bootcamp.santander.api.notificacoes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //método para obter uma lista de todos os usuários:
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //método para a criação de um novo usuário:
    public User createUser(User user){
        return userRepository.save(user);
    }

    //método para excluir um usuário informando o ID:
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
}
