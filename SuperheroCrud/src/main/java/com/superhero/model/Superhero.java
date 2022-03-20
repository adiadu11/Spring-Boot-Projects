package com.superhero.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class Superhero
{
	@Id
	@GeneratedValue
	private int sid;
	
	@NotBlank(message="Name cannot be blank!")
	@Size(min=2, message="Name length should be at least 2.")
	private String name;
	
	private String power;
	
	public Superhero()
	{
	}
	
	public Superhero(int sid,
			@NotBlank(message = "Name cannot be blank!") @Size(min = 2, message = "Name length should be at least 2.") String name,
			String power)
	{
		this.sid = sid;
		this.name = name;
		this.power = power;
	}
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	
	@Override
	public String toString() {
		return this.sid + "_" + this.name + "_" + this.power;
	}
}
