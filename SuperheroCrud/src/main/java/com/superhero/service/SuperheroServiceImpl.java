package com.superhero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superhero.exception.ResourceNotFoundException;
import com.superhero.model.Superhero;
import com.superhero.repository.SuperheroRepo;

@Service
public class SuperheroServiceImpl implements SuperheroService
{

	@Autowired
	SuperheroRepo repo;

	@Override
	public Superhero addSuperhero(Superhero hero)
	{
		return repo.save(hero);
	}

	@Override
	public Superhero getSuperhero(int hid)
	{
		return repo.findById(hid).orElseThrow(() -> new ResourceNotFoundException("Superhero not found with Id: " + hid));
	}

	@Override
	public List<Superhero> getSuperheroes()
	{
		return repo.findAll();
	}

	@Override
	public String deleteSuperhero(int hid)
	{
		Superhero hero = repo.findById(hid).orElseThrow(() -> new ResourceNotFoundException("Superhero not found with Id: " + hid));
		repo.delete(hero);
		return "Deleted: " + hid;
	}

	@Override
	public Superhero updateSuperhero(Superhero hero)
	{
		return repo.save(hero);
	}

}
