package com.superhero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.superhero.model.Superhero;

public interface SuperheroRepo extends JpaRepository<Superhero, Integer>
{
}
