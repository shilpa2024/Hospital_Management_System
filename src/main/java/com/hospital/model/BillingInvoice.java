// BillingInvoice.java
package com.hospital.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "billing_invoices")
public class BillingInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;

    @Column(name = "amount")
    private double amount;

    @Temporal(TemporalType.TIMESTAMP)  // it's stored in the database with both date and time information.
    @Column(name = "invoice_date")
    private Date invoiceDate;
    
 // Constructors, getters, and setters

	public BillingInvoice(Long id, Patients patient, double amount, Date invoiceDate) {
		super();
		this.id = id;
		this.patient = patient;
		this.amount = amount;
		this.invoiceDate = invoiceDate;
	}

	public BillingInvoice() {
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@Override
	public String toString() {
		return "BillingInvoice [id=" + id + ", patient=" + patient + ", amount=" + amount + ", invoiceDate="
				+ invoiceDate + "]";
	}


    
    
}
