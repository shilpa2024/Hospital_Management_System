// DoctorsDao.java
package com.hospital.dao;

import com.hospital.model.Doctors;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class DoctorsDao {
    private final SessionFactory sessionFactory;

    public DoctorsDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

 // Implement CRUD operations as needed
    
    public void addDoctor(Doctors doctor) { // Method to add a doctor to the database.
        Transaction transaction = null; // Declare a Transaction object and initialize it to null.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            transaction = session.beginTransaction(); // Begin a new transaction.
            session.save(doctor); // Save the doctor object.
            transaction.commit(); // Commit the transaction.
        } catch (Exception e) {
            if (transaction != null) { // If an exception occurs, check if transaction is not null.
                transaction.rollback(); // Rollback the transaction.
            }
            e.printStackTrace(); // Print exception stack trace.
        }
    }

    public Doctors getDoctorById(Long id) { // Retrieve a doctor by ID.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            return session.get(Doctors.class, id); // Get the doctor by ID.
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace.
            return null; // Return null if an exception occurs.
        }
    }

    public List<Doctors> getAllDoctors() { // Retrieve all doctors.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            return session.createQuery("FROM Doctors", Doctors.class).list(); // Query all doctors.
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace.
            return null; // Return null if an exception occurs.
        }
    }


    public Doctors getDoctorByName(String doctorName) { // Retrieve a doctor by name.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            return session.createQuery("FROM Doctors WHERE name = :doctorName", Doctors.class) // Query for the doctor by name.
                    .setParameter("doctorName", doctorName) // Set the parameter for the doctor's name.
                    .uniqueResult(); // Retrieve the unique result.
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace.
            return null; // Return null if an exception occurs.
        }
    }



	

    
}
