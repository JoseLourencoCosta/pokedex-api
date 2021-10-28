package br.com.pokedexapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pokedexapi.entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer>{

}
