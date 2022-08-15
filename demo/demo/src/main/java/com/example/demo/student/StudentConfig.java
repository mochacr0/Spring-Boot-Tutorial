package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig implements CommandLineRunner{
	
	@Autowired
	private StudentRepository studentRepository;

	public StudentConfig(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		Student student1 = new Student("student1", "student1@gmail.com", LocalDate.of(2000, Month.AUGUST, 3));
		Student student2 = new Student("student2", "student2@gmail.com", LocalDate.of(2000, Month.AUGUST, 3));
		studentRepository.saveAll(List.of(student1, student2));
	}
	/* @Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {

		};
	} */
}
