// Admissions.java
package com.hospital.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "admissions")
public class Admissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "admission_date")
    private Date admissionDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "discharge_date")
    private Date dischargeDate;
    
 // Constructors, getters, and setters

	public Admissions(Long id, Patients patient, Date admissionDate, Date dischargeDate) {
		super();
		this.id = id;
		this.patient = patient;
		this.admissionDate = admissionDate;
		this.dischargeDate = dischargeDate;
	}

	public Admissions() {
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

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	@Override
	public String toString() {
		return "Admissions [id=" + id + ", patient=" + patient + ", admissionDate=" + admissionDate + ", dischargeDate="
				+ dischargeDate + "]";
	}

    
    
}
