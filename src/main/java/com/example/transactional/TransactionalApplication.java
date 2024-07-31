package com.example.transactional;

import com.example.transactional.persistence.Person;
import com.example.transactional.persistence.PeopleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransactionalApplication {

	public static final String USERNAME = "Mark";

	public static void main(String[] args) {
		SpringApplication.run(TransactionalApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PeopleRepository peopleRepository) {
		return args -> {
			Person person = new Person();
			person.setName(USERNAME);
			peopleRepository.save(person);
		};
	}

}
