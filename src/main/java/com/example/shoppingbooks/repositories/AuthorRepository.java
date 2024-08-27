package com.example.shoppingbooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppingbooks.entities.Author;
import java.util.List;


@Repository
public interface AuthorRepository extends JpaRepository<Author,Long>{
    Author findByFirstNameAndLastName(String firstName, String lastName);
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
