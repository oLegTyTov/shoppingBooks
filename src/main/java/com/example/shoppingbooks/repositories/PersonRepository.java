package com.example.shoppingbooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppingbooks.entities.Person;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>{
Person findByUsername(String username);
boolean existsByUsername(String username);
}
