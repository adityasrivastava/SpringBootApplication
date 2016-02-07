package com.springbootapplication.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	
	private String profession;
	

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getProfession() {
		return profession;
	}
	
	public void setProfession(String profession) {
		this.profession = profession;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", profession=" + profession + "]";
	}

}
