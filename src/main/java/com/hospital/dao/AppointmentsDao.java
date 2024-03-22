// AppointmentsDao.java
package com.hospital.dao;

import com.hospital.model.Appointments;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class AppointmentsDao {
    private final SessionFactory sessionFactory;

    public AppointmentsDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    // Implement CRUD operations as needed

    public void scheduleAppointment(Appointments appointment) { // Method to schedule an appointment.
        Transaction transaction = null; // Declare a Transaction object and initialize it to null.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            transaction = session.beginTransaction(); // Begin a new transaction.
            session.save(appointment); // Save the appointment object.
            transaction.commit(); // Commit the transaction.
        } catch (Exception e) {
            if (transaction != null) { // If an exception occurs, check if transaction is not null.
                transaction.rollback(); // Rollback the transaction.
            }
            e.printStackTrace(); // Print exception stack trace.
        }
    }

    public Appointments getAppointmentById(Long id) { // Retrieve an appointment by ID.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            return session.get(Appointments.class, id); // Get the appointment by ID.
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace.
            return null; // Return null if an exception occurs.
        }
    }

    public List<Appointments> getAppointmentsByPatientId(Long patientId) { // Retrieve appointments by patient ID.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            return session.createQuery("FROM Appointments WHERE patient.id = :patientId", Appointments.class) // Query for appointments by patient ID.
                    .setParameter("patientId", patientId) // Set the parameter for the patient ID.
                    .list(); // Retrieve the list of appointments.
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace.
            return null; // Return null if an exception occurs.
        }
    }

    public List<Appointments> getAppointmentsByDoctorId(Long doctorId) { // Retrieve appointments by doctor ID.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            return session.createQuery("FROM Appointments WHERE doctor.id = :doctorId", Appointments.class) // Query for appointments by doctor ID.
                    .setParameter("doctorId", doctorId) // Set the parameter for the doctor ID.
                    .list(); // Retrieve the list of appointments.
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace.
            return null; // Return null if an exception occurs.
        }
    }



}
