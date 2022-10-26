package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create student object
			System.out.println("Creating new student object...");
			Student tempStudent = new Student("Pera", "Peric", "peraperic@gmail.com");
					
			// create a student object
			session.beginTransaction();
					
			// start a transaction
			System.out.println("Saving the student...");
			session.save(tempStudent);
			
			// save a student object
			session.getTransaction().commit();
			
			// commit transaction
			System.out.println("Done.");
				
		}
		finally {
			factory.close();
		}
	}
}
