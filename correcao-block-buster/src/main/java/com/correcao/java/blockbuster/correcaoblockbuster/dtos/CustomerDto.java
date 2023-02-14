package com.correcao.java.blockbuster.correcaoblockbuster.dtos;

import com.correcao.java.blockbuster.correcaoblockbuster.models.MovieRentModel;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Data
public class CustomerDto {


    @NotBlank
    private String name;
    @NotBlank
    @CPF
    private String cpf;
    @NotBlank
    private String email;
    @NotBlank
    private String address;


   /* private List<MovieRentModel> movieRentModelList;*/

}
