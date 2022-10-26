package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;

public class FetchJoinDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
					
			// start a transaction
			session.beginTransaction();
			
			
			// option 2: hibernate query with HQL
			
			// get the instructor from db
			int theId = 1;
			
			Query<Instructor> query = 
					session.createQuery("select i form Instructor i "
							+ "JOIN FETCH i.courses "
							+ "where i.id=:theInstructorId",
							 Instructor.class);
			
			//set parameter on query
			query.setParameter("theInstructorId", theId);
			
			// execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("aaa: Instructor: " + tempInstructor);
		
			
			// commit transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			
			System.out.println("\naaa: The session is now closed\n");
			// option 1: call getter method while session is open
			
			
			// get course for the instructor
			System.out.println("aaa: Courses: " + tempInstructor.getCourses());
						
			
			System.out.println("aaa: Done.");
				
		}
		finally {
			
			session.close();
			factory.close();
		}
	}
}
