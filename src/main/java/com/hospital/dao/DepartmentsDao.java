// DepartmentsDao.java
package com.hospital.dao;

import com.hospital.model.Departments;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class DepartmentsDao {
    private final SessionFactory sessionFactory;

    public DepartmentsDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

 // Implement CRUD operations as needed
    
    public void addDepartment(Departments department) { // Method to add a department to the database.
        Transaction transaction = null; // Declare a Transaction object and initialize it to null.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            transaction = session.beginTransaction(); // Begin a new transaction.
            session.save(department); // Save the department object.
            transaction.commit(); // Commit the transaction.
        } catch (Exception e) {
            if (transaction != null) { // If an exception occurs, check if transaction is not null.
                transaction.rollback(); // Rollback the transaction.
            }
            e.printStackTrace(); // Print exception stack trace.
        }
    }

    public Departments getDepartmentById(Long id) { // Retrieve a department by ID.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            return session.get(Departments.class, id); // Get the department by ID.
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace.
            return null; // Return null if an exception occurs.
        }
    }

    public List<Departments> getAllDepartments() { // Retrieve all departments.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            return session.createQuery("FROM Departments", Departments.class).list(); // Query all departments.
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace.
            return null; // Return null if an exception occurs.
        }
    }

    public Departments getDepartmentByName(String departmentName) { // Retrieve a department by name.
        try (Session session = sessionFactory.openSession()) { // Open a new Hibernate session.
            return session.createQuery("FROM Departments WHERE name = :departmentName", Departments.class) // Query for the department by name.
                    .setParameter("departmentName", departmentName) // Set the parameter for the department's name.
                    .uniqueResult(); // Retrieve the unique result.
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace.
            return null; // Return null if an exception occurs.
        }
    }


    
}
