// Doctors.java
package com.hospital.model;

import javax.persistence.*;



@Entity
@Table(name = "doctors")
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departments department;
    
 // Constructors, getters, and setters

	public Doctors(Long id, String name, Departments department) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
	}

	public Doctors() {
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

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Doctors [id=" + id + ", name=" + name + ", department=" + department + "]";
	}


    
    
}
