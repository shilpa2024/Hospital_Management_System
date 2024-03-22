package com.hospital.main; // my package

import com.hospital.dao.*;
import com.hospital.model.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class HospitalMainClass {
    public static void main(String[] args) {
        // Create a SessionFactory using Hibernate configuration
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Create DAO instances
        PatientsDao patientsDao = new PatientsDao(sessionFactory);
        DoctorsDao doctorsDao = new DoctorsDao(sessionFactory);
        DepartmentsDao departmentsDao = new DepartmentsDao(sessionFactory);
        AdmissionsDao admissionsDao = new AdmissionsDao(sessionFactory);
        AppointmentsDao appointmentsDao = new AppointmentsDao(sessionFactory);
        MedicalRecordsDao medicalRecordsDao = new MedicalRecordsDao(sessionFactory);
        BillingInvoiceDao billingInvoiceDao = new BillingInvoiceDao(sessionFactory);

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Switch case menu for console output
        int choice = 0;
        do {
            System.out.println("********** Hospital Management System **********");
            System.out.println("1. Add a Patient");
            System.out.println("2. Add a Department");
            System.out.println("3. Add a Doctor");
            System.out.println("4. Schedule an Appointment");
            System.out.println("5. Admit a Patient");
            System.out.println("6. Add a Medical Record");
            System.out.println("7. Add a Billing Invoice");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // Adding a patient
                    System.out.println("Enter patient details:");
                    System.out.print("Name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("Age: ");
                    int patientAge = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Gender: ");
                    String patientGender = scanner.nextLine();

                    Patients patient = new Patients(null, patientName, patientAge, patientGender);
                    patientsDao.addPatient(patient);
                    System.out.println("Added Patient: " + patient);
                    break;
                case 2:
                    // Adding a department
                    System.out.println("Enter department details:");
                    System.out.print("Name: ");
                    String departmentName = scanner.nextLine();

                    Departments department = new Departments(null, departmentName);
                    departmentsDao.addDepartment(department);
                    System.out.println("Added Department: " + department);
                    break;
                case 3:
                    // Adding a doctor
                    System.out.println("Enter doctor details:");
                    System.out.print("Name: ");
                    String doctorName = scanner.nextLine();

                    System.out.print("Enter department name: ");
                    String departmentNameForDoctor = scanner.nextLine();
                    Departments departmentForDoctor = departmentsDao.getDepartmentByName(departmentNameForDoctor);

                    Doctors doctor = new Doctors(null, doctorName, departmentForDoctor);
                    doctorsDao.addDoctor(doctor);
                    System.out.println("Added Doctor: " + doctor);
                    break;
                case 4:
                    // Adding an appointment
                    System.out.println("Schedule an Appointment:");

                    // Input patient ID
                    System.out.print("Enter Patient ID: ");
                    Long patientId = scanner.nextLong();
                    scanner.nextLine(); // Consume newline character

                    // Input doctor ID
                    System.out.print("Enter Doctor ID: ");
                    Long doctorId = scanner.nextLong();
                    scanner.nextLine(); // Consume newline character

                    // Input appointment date
                    System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                    String appointmentDateStr = scanner.nextLine();
                    java.util.Date appointmentDate = null;
                    try {
                        appointmentDate = new SimpleDateFormat("yyyy-MM-dd").parse(appointmentDateStr); // Attempt to parse the appointment date string.
                    } catch (ParseException e) {
                        e.printStackTrace(); // Print exception stack trace if parsing fails.
                    }

                    // Get patient and doctor objects
                    Patients patients = patientsDao.getPatientById(patientId);
                    Doctors doctors = doctorsDao.getDoctorById(doctorId);

                    // Check if patient and doctor exist
                    if (patients != null && doctors != null) {
                        // Create appointment object
                        Appointments appointment = new Appointments(null, patients, doctors, new Date(appointmentDate.getTime()));

                        // Schedule appointment
                        appointmentsDao.scheduleAppointment(appointment);
                        System.out.println("Appointment Scheduled: " + appointment);
                    } else {
                        System.out.println("Patient or Doctor does not exist. Please check IDs and try again.");
                    }
                    break;

                case 5:
                    // Adding an admission
                    System.out.println("Admit a Patient:");

                    // Input patient ID
                    System.out.print("Enter Patient ID: ");
                    Long patientIdForAdmission = scanner.nextLong();
                    scanner.nextLine(); // Consume newline character

                    // Input admission date
                    System.out.print("Enter Admission Date (YYYY-MM-DD): ");
                    String admissionDateStr = scanner.nextLine();
                    java.util.Date admissionDate = null;
                    try {
                        admissionDate = new SimpleDateFormat("yyyy-MM-dd").parse(admissionDateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // Input discharge date
                    System.out.print("Enter Discharge Date (YYYY-MM-DD): ");
                    String dischargeDateStr = scanner.nextLine();
                    java.util.Date dischargeDate = null;
                    try {
                        dischargeDate = new SimpleDateFormat("yyyy-MM-dd").parse(dischargeDateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // Get patient object
                    Patients patientForAdmission = patientsDao.getPatientById(patientIdForAdmission);

                    // Check if patient exists
                    if (patientForAdmission != null) {
                        // Create admission object
                        Admissions admission = new Admissions(null, patientForAdmission, new Date(admissionDate.getTime()), new Date(dischargeDate.getTime()));

                        // Admit patient
                        admissionsDao.admitPatient(admission);
                        System.out.println("Patient Admitted: " + patientForAdmission.getName() + " on " + admission.getAdmissionDate() + " to " + admission.getDischargeDate());
                    } else {
                        System.out.println("Patient does not exist. Please check ID and try again.");
                    }
                    break;

                case 6:
                    // Adding a medical record
                    System.out.println("Add a Medical Record:");

                    // Input patient ID
                    System.out.print("Enter Patient ID: ");
                    Long patientIdForMedicalRecord = scanner.nextLong();
                    scanner.nextLine(); // Consume newline character

                    // Input doctor name
                    System.out.print("Enter Doctor Name: ");
                    String doctorNameForMedicalRecord = scanner.nextLine();

                    // Input medical record description
                    System.out.print("Enter Medical Record Description: ");
                    String medicalRecordDescription = scanner.nextLine();

                    // Get patient object
                    Patients patientForMedicalRecord = patientsDao.getPatientById(patientIdForMedicalRecord);

                    // Check if patient exists
                    if (patientForMedicalRecord != null) {
                        // Get doctor object by name
                        Doctors doctorForMedicalRecord = doctorsDao.getDoctorByName(doctorNameForMedicalRecord);

                        // Check if doctor exists
                        if (doctorForMedicalRecord != null) {
                            // Create medical record object
                            MedicalRecords medicalRecord = new MedicalRecords(null, patientForMedicalRecord, doctorForMedicalRecord, medicalRecordDescription);

                            // Add medical record
                            medicalRecordsDao.addMedicalRecord(medicalRecord);
                            System.out.println("Medical Record Added: " + medicalRecord);
                        } else {
                            System.out.println("Doctor does not exist. Please check name and try again.");
                        }
                    } else {
                        System.out.println("Patient does not exist. Please check ID and try again.");
                    }
                    break;

                case 7:
                    // Adding a billing invoice
                    System.out.println("Add a Billing Invoice:");

                    // Input patient ID
                    System.out.print("Enter Patient ID: ");
                    Long patientIdForInvoice = scanner.nextLong();
                    scanner.nextLine(); // Consume newline character

                    // Input billing amount
                    System.out.print("Enter Billing Amount: ");
                    double billingAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character

                    // Get patient object
                    Patients patientForInvoice = patientsDao.getPatientById(patientIdForInvoice);

                    // Check if patient exists
                    if (patientForInvoice != null) {
                        // Create billing invoice object
                        BillingInvoice billingInvoice = new BillingInvoice(null, patientForInvoice, billingAmount, new Date(0));

                        // Add billing invoice
                        billingInvoiceDao.addBillingInvoice(billingInvoice);
                        System.out.println("Billing Invoice Added: " + billingInvoice);
                    } else {
                        System.out.println("Patient does not exist. Please check ID and try again.");
                    }
                    break;

                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        } while (choice != 8);

        // Closing the Scanner
        scanner.close();
    }
}
