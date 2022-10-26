package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create 3 student objects
			System.out.println("Creating 3 student objects...");
			Student tempStudent1 = new Student("Sima", "Simic", "simasimic@gmail.com");
			Student tempStudent2 = new Student("Ana", "Anic", "anaanic@gmail.com");
			Student tempStudent3 = new Student("Milica", "Milic", "milicamilic@gmail.com");
					
			// start a transaction
			session.beginTransaction();
					
			// start a transaction
			System.out.println("Saving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
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

