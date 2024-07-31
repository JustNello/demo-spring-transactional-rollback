package com.example.transactional.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<Person, Long> {

    public Person findByName(String name);
}
