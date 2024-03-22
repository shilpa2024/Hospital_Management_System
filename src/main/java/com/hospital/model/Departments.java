// Departments.java
package com.hospital.model;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //the primary value(auto-increment) during insertion.
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

 // Constructors, getters, and setters
    
	public Departments(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Departments() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	@Override
	public String toString() {
		return "Departments [id=" + id + ", name=" + name + "]";
	}


    
    
}
