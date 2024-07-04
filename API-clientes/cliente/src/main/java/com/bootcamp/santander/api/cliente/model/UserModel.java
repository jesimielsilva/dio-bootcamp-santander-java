package com.bootcamp.santander.api.cliente.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity(name = "user")
@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private int id;

    private String name;
    private String cpf;
    private LocalDate date_of_birth; // data de nasc

    @CreationTimestamp
    private LocalDateTime createdAt;
}
