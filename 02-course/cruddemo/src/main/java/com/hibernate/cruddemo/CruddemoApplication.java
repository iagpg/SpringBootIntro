package com.hibernate.cruddemo;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hibernate.cruddemo.DAO.StudentDAO;
import com.hibernate.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	//will run after beans have been created
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {	
			System.out.println("CommandLineRunner running... ");
			
			//createStudent(studentDAO);
			//createMultipleStudents(studentDAO);
			//findStudent(studentDAO);
			//findAllStudents(studentDAO);
			//findStudentLastName(studentDAO,"Jackson");
			//updateStudent(studentDAO);
			//removeStudent(studentDAO,5);
			removeAllStudents(studentDAO);
			 

			
		};	
	}


	private void removeAllStudents(StudentDAO studentDAO) {
		Integer DeletedCounts = studentDAO.deleteAll();
		if (DeletedCounts > 0) {
			System.out.println("All students have been removed. Total deleted: " + DeletedCounts);
		} else {
			System.out.println("No students were removed.");
		}
	}

	private void removeStudent(StudentDAO studentDAO, Integer id) {
		studentDAO.delete(id);
		System.out.println("removed student by id " + id);
	}	

	private void updateStudent(StudentDAO studentDAO) {
		// find the student by id
		Student studentFound = studentDAO.findById(1);
		
		if (studentFound != null){
			studentFound.setLastName("Smith");
			studentDAO.update(studentFound);
			System.err.println("Student updated: " + studentFound.getFirstName() + " " + studentFound.getLastName());
		}	
	}

	private void findStudentLastName(StudentDAO studentDAO, String lastName) {
		List<Student> studentObject = studentDAO.findByLastName(lastName);
		System.err.println("Searching for students with last name: " + lastName);
		if (studentObject.isEmpty()) {
			System.out.println("No students found with last name: " + lastName);
		} else {
			for (Student student : studentObject) {
				System.out.println(student);
			}
		}
	}

	private List<Student> findAllStudents(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();
		for (Student student : students) {
			System.out.println(student);
		}
		return students;
	}
	private Student findStudent(StudentDAO studentDAO) {
		// TODO Auto-generated method stub
		try{
			Student foundStudent = studentDAO.findById(1);
			System.out.println(foundStudent);
			return foundStudent;
		}catch (Exception e) {
			System.err.println("Student not found");
			return null;
		}
	}

	private void createMultipleStudents(StudentDAO studentDAO  ) {
		Student studentObject = new Student("Paul","Marker","paulmarker@outlook.com");
		Student studentObject2 = new Student("Peter","Jackson","peterjackson@yahoo.com.br");
		Student studentObject3 = new Student("Mariah","Cara","mariahcara@gmail.com");
		
		List<Student> students = Arrays.asList(studentObject, studentObject2, studentObject3);
		studentDAO.saveAll(students);
		System.out.println("Students have been saved");
	}	

	private void createStudent(StudentDAO studentDAO) {
		Student studentObject = new Student("John","Doe","JohnDoe@email.com.br");
		studentDAO.save(studentObject);

		System.out.println("Student saved: " + studentObject.getFirstName() + " generated id: " + studentObject.getId());
	}
}
