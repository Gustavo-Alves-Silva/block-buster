package com.correcao.java.blockbuster.correcaoblockbuster.repositories;

import com.correcao.java.blockbuster.correcaoblockbuster.models.MovieRentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRentRepository extends JpaRepository<MovieRentModel, UUID> {
}
