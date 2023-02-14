package com.correcao.java.blockbuster.correcaoblockbuster.services;

import com.correcao.java.blockbuster.correcaoblockbuster.models.MovieRentModel;
import com.correcao.java.blockbuster.correcaoblockbuster.repositories.MovieRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieRentService {

    @Autowired
    private MovieRentRepository  movieRentRepository;


    public MovieRentModel save(MovieRentModel movieRentModel) {
        return movieRentRepository.save(movieRentModel);
    }
}
