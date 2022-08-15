package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {
	
	@Autowired
	private final StudentService studentService;	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@PostMapping
	public ResponseEntity<Student> createNewStudent(@RequestBody Student student) {
		Student createadUser;
		try {
			createadUser = studentService.createNewStudent(student);		
		}
		catch (Exception e) {
			return new ResponseEntity< >(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity< >(createadUser, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "{studentId}")
	public ResponseEntity<Student> deleteStudentById(@PathVariable("studentId") Long studentId) {
		try {
			studentService.deleteStudentById(studentId);
		}
		catch(Exception e) {
			return new ResponseEntity< >(HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity< >(HttpStatus.OK); 
	}
	
	@PutMapping(path = "{studentId}")
	public ResponseEntity<Student> udpateStudent(@PathVariable("studentId") Long studentId, 
			@RequestParam(required = false) String name, 
			@RequestParam(required = false) String email) {
		Student updatedStudent;
		try {
			updatedStudent = studentService.updateStudent(studentId, email, name);
		}
		catch (Exception e) {
			return new ResponseEntity< >(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity< >(updatedStudent, HttpStatus.OK);
	}
	
}
