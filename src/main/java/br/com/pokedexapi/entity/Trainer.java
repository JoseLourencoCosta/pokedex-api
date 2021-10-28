package br.com.pokedexapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "trainer")
public class Trainer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) /*gerar id e incrementar +1 a cada novo id*/
	@Column(name = "id")
	@Getter
	@Setter
	private Integer id;
	
	@Column(name = "gender", nullable = false, length = 1)
	@Getter
	@Setter
	@NotNull(message = "gender is required!")
	private String gender;
	
	
	@Column(name = "name", nullable = false, length = 50)
	@Getter
	@Setter
	@NotEmpty(message = "Name cannot be empty!")
	@Size(min = 3, max = 50, message = "Name: min 3 and max 50")
	private String name;
	
	@Column(name = "city", nullable = false)
	@Getter
	@Setter
	private String city;
	
	
	
	

}


	
