package com.student.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.student.model.Student;
import com.student.service.StudentService;

@RestController
public class StudentController 
{
	@Autowired
	private StudentService studentService;
	
	//get All Student
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents()
	{
		List<Student> list = studentService.getAllStudent();
		if(list.size()<=0)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	//get all student by id
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") int id)
	{
		Student student = studentService.getStudentById(id);
		if(student == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(student));
	}
	
	//add student
	@PostMapping("/students")
	public ResponseEntity<Student> addStudent(@RequestBody Student student)
	{
		Student s = null;
		try {
			s = studentService.addStudent(student);
			return ResponseEntity.of(Optional.of(s));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//delete student
	@DeleteMapping("/students/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") int studentId)
	{
		try 
		{
			this.studentService.deleteStudent(studentId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//update Student
	@PutMapping("/students/{studentId}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable("studentId") int studentId)
	{
		try
		{
			this.studentService.updateStudent(studentId, student);
			return ResponseEntity.ok().body(student);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
