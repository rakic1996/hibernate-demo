package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			/*
			// create the objects
			Instructor tempInstructor =
					new Instructor("MIka", "Mikic", "mikamikic@gmail.com");
					
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("htttp://ftn.ans.ac.rs/youtube", "Hiking");
			*/
			
			// create the objects
			Instructor tempInstructor =
					new Instructor("Walter", "White", "walterwhite@gmail.com");
					
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("htttp://www.youtube.com", "Cooking");
			
			
			//associate the objects 
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction
			session.beginTransaction();
			
			// save the instructor 
			//
			// Note: this will also save the details object
			// because of CascadeType.ALL
			//
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
			
			
			// commit transaction
			System.out.println("Done.");
				
		}
		finally {
			session.close();
			factory.close();
		}
	}
}
