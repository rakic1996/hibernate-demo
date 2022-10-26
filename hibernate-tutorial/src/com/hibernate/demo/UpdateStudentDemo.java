package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId =1;
			   

			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			
			// update ime za odredjenog studenta
			// retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student...");
			myStudent.setFirstName("John");
			
			// commit the transaction
			session.getTransaction().commit();
			
			
			// novi deo koda(update email za sve studente)
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// update email for all students
			System.out.println("Update email for all students");
			
			session.createQuery("update Student set email='yahoo.com'")
				.executeUpdate();
			
			// commit the transactions
			session.getTransaction().commit();
			
			System.out.println("Done.");
				
		}
		finally {
			factory.close();
		}
	}
}
