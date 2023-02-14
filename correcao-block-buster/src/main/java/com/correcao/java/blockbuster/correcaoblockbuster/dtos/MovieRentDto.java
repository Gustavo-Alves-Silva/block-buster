package com.correcao.java.blockbuster.correcaoblockbuster.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MovieRentDto {

    @NotBlank
    private String movieTitle;

    @NotBlank
    private String movieGender;

    @NotBlank
    private double moviePrice;

    @NotBlank
    private UUID customerId;

}

