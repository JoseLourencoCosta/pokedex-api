package br.com.pokedexapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pokedexapi.entity.Pokemon;
import br.com.pokedexapi.repository.PokemonRepository;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

	@Autowired
	private PokemonRepository pokemonRepository;

	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World";
	}

	@PostMapping()
	public Pokemon save(@RequestBody @Valid Pokemon pokemon) {
		return this.pokemonRepository.save(pokemon);
	}

	@GetMapping()
	public List<Pokemon> findAll() {
		List<Pokemon> pokemons = this.pokemonRepository.findAll();
		return pokemons;
		// return this.pokemonRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pokemon> findById(@PathVariable("id") Integer id) {
		Optional<Pokemon> resultOne = this.pokemonRepository.findById(id);

		if (resultOne.isPresent()) {
			Pokemon pokemon = resultOne.get();

			return new ResponseEntity<Pokemon>(pokemon, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		this.pokemonRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pokemon> update(@PathVariable("id") Integer id, @RequestBody Pokemon pokemonJson) {
		Optional<Pokemon> result = pokemonRepository.findById(id);

		if (result.isPresent()) {
			Pokemon pokemonFromDataBase = result.get();
			pokemonFromDataBase.setDescription(pokemonJson.getDescription());
			pokemonFromDataBase.setName(pokemonJson.getName());
			pokemonFromDataBase.setTypes(pokemonJson.getTypes());
			pokemonFromDataBase.setImageUrl(pokemonJson.getImageUrl());
			pokemonFromDataBase.setNumber(pokemonJson.getNumber());
			this.pokemonRepository.save(pokemonFromDataBase);
			return new ResponseEntity<>(pokemonFromDataBase, HttpStatus.OK);

		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
