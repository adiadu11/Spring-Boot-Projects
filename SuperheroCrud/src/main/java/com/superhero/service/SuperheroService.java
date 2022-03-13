package com.superhero.service;

import java.util.List;

import com.superhero.model.Superhero;

public interface SuperheroService
{
	Superhero addSuperhero(Superhero hero);
	
	Superhero getSuperhero(int hid);
	
	List<Superhero> getSuperheroes();
	
	String deleteSuperhero(int hid);
	
	Superhero updateSuperhero(Superhero hero); 
}
