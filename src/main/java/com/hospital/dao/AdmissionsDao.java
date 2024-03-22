// AdmissionDao.java
package com.hospital.dao;

import com.hospital.model.Admissions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class AdmissionsDao {
    private final SessionFactory sessionFactory;

    public AdmissionsDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Implement CRUD operations as needed
    
    public void admitPatient(Admissions admissions) { // Method to admit a patient.
        Transaction transaction = null; // Declare a Transaction object and initialize it to null.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            transaction = session.beginTransaction(); // Begin a new transaction.
            session.save(admissions); // Save the admissions object.
            transaction.commit(); // Commit the transaction.
        } catch (Exception e) {
            if (transaction != null) { // If an exception occurs, check if transaction is not null.
                transaction.rollback(); // Rollback the transaction.
            }
            e.printStackTrace(); // Print exception stack trace.
        }
    }

    public Admissions getAdmissionsById(Long id) { // Retrieve admissions by ID.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            return session.get(Admissions.class, id); // Get the admissions by ID.
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace.
            return null; // Return null if an exception occurs.
        }
    }

    public List<Admissions> getAllAdmissions() { // Retrieve all admissions.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            return session.createQuery("FROM Admissions", Admissions.class).list(); // Query all admissions.
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace.
            return null; // Return null if an exception occurs.
        }
    }

}
