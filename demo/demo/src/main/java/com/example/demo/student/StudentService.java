package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		//return List.of(new Student(1L, "Name1", "name1@gmail.com", LocalDate.of(2000, Month.JANUARY, 5), 21));
		return studentRepository.findAll();
	}

	public Student createNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("Email is already exists"); 
		}
		return studentRepository.save(student);
	}
	
	public void deleteStudentById(Long studentId) {
		boolean isStudentExists = studentRepository.existsById(studentId);
		if (!isStudentExists) {
			throw new IllegalStateException("Student is not exists");
		}
		studentRepository.deleteById(studentId);
	}
		
	@Transactional
	public Student updateStudent(Long studentId, String email, String name) {
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student is not exists"));
		if (name != null && name.length() > 0) {
			student.setName(name);
		}
		if (email != null && email.length() > 0) {
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if (studentOptional.isPresent()) {
				throw new IllegalStateException("Email is already exists");
			}
			student.setEmail(email);
		}
		return student;
		
	
	}

}
