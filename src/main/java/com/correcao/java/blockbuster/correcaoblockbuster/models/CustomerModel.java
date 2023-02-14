package com.correcao.java.blockbuster.correcaoblockbuster.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.swing.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "TB_COSTUMER")
public class CustomerModel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false,  unique = true, length = 130)
    private String name;

    @Column(nullable = false, unique = true)
    @CPF
    private String cpf;

    @Column(nullable = false, unique = true, length = 130)
    private String email;

    @Column(nullable = false, length = 300)
    private String address;

    @OneToMany(mappedBy = "customerModel")
    @JsonManagedReference
    private List<MovieRentModel> movieRentModelList;


}
