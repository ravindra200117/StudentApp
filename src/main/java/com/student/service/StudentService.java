package com.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.student.dao.StudentRepository;
import com.student.model.Student;

@Component
public class StudentService 
{
	@Autowired
	private StudentRepository studentRepository;
	
	//get all Student
	public List<Student> getAllStudent()
	{
		return this.studentRepository.findAll();
		
	}
	
	//get all Student by id
	public Student getStudentById(int id)
	{
		Student student = null;
		
		try {
			
			student = this.studentRepository.findById(id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return student;
	}
	
	//add student
	
	public Student addStudent(Student student)
	{
		return this.studentRepository.save(student);
	}
	
	//update Student
	
	public Student updateStudent(int id, Student student)
	{
		
		student.setId(id);
		
		return studentRepository.save(student);
	}
	
	//delete student
	public void deleteStudent(int id)
	{
		studentRepository.deleteById(id);
	}
		
}
