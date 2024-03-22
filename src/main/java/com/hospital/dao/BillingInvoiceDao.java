// BillingInvoiceDao.java
package com.hospital.dao;

import com.hospital.model.BillingInvoice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class BillingInvoiceDao {
    private final SessionFactory sessionFactory;

    public BillingInvoiceDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
 // Implement CRUD operations as needed

    public void addBillingInvoice(BillingInvoice billingInvoice) { // Method to add a billing invoice to the database.
        Transaction transaction = null; // Declare a Transaction object and initialize it to null.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            transaction = session.beginTransaction(); // Begin a new transaction.
            session.save(billingInvoice); // Save the billing invoice object.
            transaction.commit(); // Commit the transaction.
        } catch (Exception e) {
            if (transaction != null) { // If an exception occurs, check if transaction is not null.
                transaction.rollback(); // Rollback the transaction.
            }
            e.printStackTrace(); // Print exception stack trace.
        }
    }

    public BillingInvoice getBillingInvoiceById(Long id) { // Retrieve a billing invoice by ID.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            return session.get(BillingInvoice.class, id); // Get the billing invoice by ID.
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace.
            return null; // Return null if an exception occurs.
        }
    }

    public List<BillingInvoice> getBillingInvoicesByPatientId(Long patientId) { // Retrieve billing invoices by patient ID.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            return session.createQuery("FROM BillingInvoice WHERE patient.id = :patientId", BillingInvoice.class) // Query for billing invoices by patient ID.
                    .setParameter("patientId", patientId) // Set the parameter for the patient ID.
                    .list(); // Retrieve the list of billing invoices.
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace.
            return null; // Return null if an exception occurs.
        }
    }

    
}
