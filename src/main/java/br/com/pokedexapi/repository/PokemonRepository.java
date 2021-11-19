package br.com.pokedexapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pokedexapi.entity.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{
	
	List<Pokemon> findAllByOrderByNumberAsc();
	List<Pokemon> findByNameContainingIgnoreCaseOrderByNumberAsc (String name);

}
