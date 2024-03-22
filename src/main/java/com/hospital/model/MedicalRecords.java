// MedicalRecords.java
package com.hospital.model;

import javax.persistence.*;

@Entity
@Table(name = "medical_records")
public class MedicalRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctors doctor;

    @Column(name = "description")
    private String description;
    
    // Constructors, getters, and setters

	public MedicalRecords(Long id, Patients patient, Doctors doctor, String description) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.description = description;
	}

	public MedicalRecords() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patients getPatient() {
		return patient;
	}

	public void setPatient(Patients patient) {
		this.patient = patient;
	}

	public Doctors getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctors doctor) {
		this.doctor = doctor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MedicalRecords [id=" + id + ", patient=" + patient + ", doctor=" + doctor + ", description="
				+ description + "]";
	}



   
    
}
