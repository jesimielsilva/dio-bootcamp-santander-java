package com.bootcamp.santander.api.cliente.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name = "conta")
public class ContaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id_conta;

    // @OneToOne(fetch = FetchType.LAZY)
    @OneToOne(fetch = FetchType.EAGER) // Afeta o desempenho se tiver um grande base de dados
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private UserModel user;

    private String email;
    private String password;
    private int account_number;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setUserById(int userId) {
        UserModel user = new UserModel();
        user.setId(userId);
        this.user = user;
    }

}
