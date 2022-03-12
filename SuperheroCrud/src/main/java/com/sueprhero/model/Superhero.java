package com.sueprhero.model;

public class Superhero
{
	private int sid;
	private String name;
	private String power;
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
