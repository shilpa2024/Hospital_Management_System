// PatientsDao.java
package com.hospital.dao;

import com.hospital.model.Patients;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class PatientsDao {
    private final SessionFactory sessionFactory;

    public PatientsDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    // Implement CRUD operations as needed

    public void addPatient(Patients patient) {
        Transaction transaction = null;  //Declare a Transaction object and initialize it to null.
        try (Session session = sessionFactory.openSession()) {  //Open a new Hibernate session using the session factory.
            transaction = session.beginTransaction(); //Begin a new transaction associated with the session.
            session.save(patient);  //Save the patient object to the database using the current session.
            transaction.commit();   //Commit the transaction to make the changes permanent.
        } catch (Exception e) {
            if (transaction != null) {      //Catch any exceptions that occur during the process.
                transaction.rollback();     //If an exception occurs, roll back the transaction to maintain data consistency.
            }
            e.printStackTrace();            //Print the stack trace of the exception for debugging purposes.
        }
    }

    public Patients getPatientById(Long id) { // Retrieve a patient by ID.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            return session.get(Patients.class, id); // Get the patient by ID.
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace.
            return null; // Return null if an exception occurs.
        }
    }

    public List<Patients> getAllPatients() { // Retrieve all patients.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            return session.createQuery("FROM Patients", Patients.class).list(); // Query all patients.
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace.
            return null; // Return null if an exception occurs.
        }
    }


  
}
