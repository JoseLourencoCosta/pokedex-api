package br.com.pokedexapi.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Entity(name = "pokemon")
public class Pokemon {

	@Id
	@Column(name = "number")
	@Setter
	@Getter
	@NotNull(message = "Number is required!")
	private Integer number;
	
	@Column(name = "name", nullable = false, unique = true)
	@Setter
	@Getter
	@NotEmpty(message = "Name cannot be empty!")
	@Size(min = 3, max = 255, message = "Name: min 3 and max 255")
	private String name;
	
	@Column(name = "description")
	@Setter
	@Getter
	private String description;
	
	@Column(name = "image_url", nullable = false)
	@Getter
	@Setter
	@NotBlank(message = "image URL cannot be empty!")
	private String imageUrl;
	
	@ElementCollection
	@CollectionTable(name = "pokemon_type", joinColumns = @JoinColumn(name = "pokemon_number"))
	@Column(name = "type")
	@Getter
	@Setter
	private List<String> types;
}
