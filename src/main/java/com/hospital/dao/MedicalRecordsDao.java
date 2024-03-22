package com.hospital.dao;

import com.hospital.model.MedicalRecords;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class MedicalRecordsDao {
    private final SessionFactory sessionFactory;

    public MedicalRecordsDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    // Implement CRUD operations as needed

    public void addMedicalRecord(MedicalRecords medicalRecord) { // Method to add a medical record to the database.
        Transaction transaction = null; // Declare a Transaction object and initialize it to null.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session using the session factory.
            transaction = session.beginTransaction(); // Begin a new transaction associated with the session.
            session.save(medicalRecord); // Save the medical record object to the database using the current session.
            transaction.commit(); // Commit the transaction to make the changes permanent.
        } catch (Exception e) {
            if (transaction != null) { // If an exception occurs, check if the transaction is not null.
                transaction.rollback(); // Roll back the transaction to maintain data consistency.
            }
            e.printStackTrace(); // Print the stack trace of the exception for debugging purposes.
        }
    }


    public MedicalRecords getMedicalRecordById(Long id) { // Method to retrieve a medical record by its ID.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session using the session factory.
            return session.get(MedicalRecords.class, id); // Retrieve the medical record by ID using session.get() method.
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace of the exception for debugging purposes.
            return null; // Return null if an exception occurs.
        }
    }

    public List<MedicalRecords> getAllMedicalRecords() { // Method to retrieve all medical records.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session using the session factory.
            return session.createQuery("FROM MedicalRecords", MedicalRecords.class).list(); // Retrieve all medical records using HQL query.
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace of the exception for debugging purposes.
            return null; // Return null if an exception occurs.
        }
    }


   
}
