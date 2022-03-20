package com.superhero.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.superhero.model.Superhero;
import com.superhero.service.SuperheroService;

@RestController
public class SuperheroController
{
	@Autowired
	SuperheroService superheroService;
	
	@PostMapping("superhero")
	public Superhero addSuperhero(@Valid @RequestBody Superhero hero)
	{
		return superheroService.addSuperhero(hero);
	}
	
	@GetMapping("superhero/{sid}")
	public Superhero getSuperhero(@PathVariable int sid)
	{
		return superheroService.getSuperhero(sid);
	}
	
	@GetMapping("superheroes")
	public List<Superhero> getSuperheroes()
	{
		return superheroService.getSuperheroes();
	}
	
	@DeleteMapping("superhero/{sid}")
	public String deleteSuperhero(@PathVariable int sid)
	{
		return superheroService.deleteSuperhero(sid);
	}
	
	@PutMapping("superhero")
	public Superhero updateSuperhero(@Valid @RequestBody Superhero hero)
	{
		return superheroService.updateSuperhero(hero);
	}
}
