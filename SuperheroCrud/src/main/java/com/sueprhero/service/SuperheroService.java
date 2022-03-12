package com.sueprhero.service;

import java.util.List;

import com.sueprhero.model.Superhero;

public interface SuperheroService
{
	Superhero addHero(Superhero hero);
	
	Superhero getHero(int hid);
	
	List<Superhero> getHeroes();
	
	int deleteHero(int hid);
	
	Superhero updateHero(Superhero hero); 
}
