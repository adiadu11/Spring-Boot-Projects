package com.sueprhero;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.superhero.SuperheroCrudApplication;
import com.superhero.exception.ResourceNotFoundException;
import com.superhero.model.Superhero;
import com.superhero.repository.SuperheroRepo;
import com.superhero.service.SuperheroService;

//ToDo: Add negative scenarios as well.
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuperheroCrudApplication.class) //Add classes property as the SuperheroCrudApplication was moved to another directory after creation.
class SuperheroCrudApplicationTests {

	@Autowired
	private SuperheroService superheroService;
	
	@MockBean
	private SuperheroRepo repo;
	
	@Test
	public void addSuperhero()
	{
		Superhero hero = new Superhero(1, "Hawkeye", "Arrows");
		when(repo.save(hero)).thenReturn(hero);
		assertEquals(hero, superheroService.addSuperhero(hero));
	}

	@Test
	public void getSuperhero()
	{
		int id = 1;
		Superhero hero = new Superhero(id, "Hawkeye", "Arrows");
		Optional<Superhero> heroObj = Optional.of(hero);
		when(repo.findById(id)).thenReturn(heroObj);
		assertEquals(hero, superheroService.getSuperhero(1));
	}

	@Test
	public void getSuperheroesTest()
	{
		when(repo.findAll()).thenReturn(Stream.of(
				new Superhero(1, "Silver Surfer", "Speed"),
				new Superhero(2, "Flash", "Speed"))
				.collect(Collectors.toList()));
		assertEquals(2, superheroService.getSuperheroes().size());
	}

	@Test
	public void deleteSuperhero()
	{
		int hid = 1;
		Superhero heroObj = new Superhero(hid, "Wolverine", "Healing");
		Optional<Superhero> hero = Optional.of(heroObj);
		when(repo.findById(hid)).thenReturn(hero);
		superheroService.deleteSuperhero(hid);
		//Verify that delete function of repo is called exactly once.
		verify(repo, times(1)).delete(heroObj);
	}
	
	@Test
	public void updateSuperhero()
	{
		Superhero hero = new Superhero(1, "Hawkeye", "Arrows");
		when(repo.save(hero)).thenReturn(hero);
		assertEquals(hero, superheroService.addSuperhero(hero));
	}

}
