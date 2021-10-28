package br.com.pokedexapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pokedexapi.entity.Trainer;
import br.com.pokedexapi.repository.TrainerRepository;

@RestController
@RequestMapping("/trainers") //toda requisição vai utilizar essa chamada
public class TrainerController {
	
	@Autowired //new 
	private TrainerRepository trainerRepository; 
	
	@GetMapping()
	public List<Trainer> findAll() {
		return this.trainerRepository.findAll();
	}
	
	@PostMapping()
	public Trainer save(@RequestBody @Valid Trainer trainer) {
		return this.trainerRepository.save(trainer);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		this.trainerRepository.deleteById(id);
	}

}
