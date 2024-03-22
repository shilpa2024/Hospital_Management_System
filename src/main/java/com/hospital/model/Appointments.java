// Appointments.java
package com.hospital.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointments {
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_date")
    private Date appointmentDate;
    
    // Constructors, getters, and setters

	public Appointments(Long id, Patients patient, Doctors doctor, Date appointmentDate) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.appointmentDate = appointmentDate;
	}

	public Appointments() {
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

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	@Override
	public String toString() {
		return "Appointments [id=" + id + ", patient=" + patient + ", doctor=" + doctor + ", appointmentDate="
				+ appointmentDate + "]";
	}


   
    
}
