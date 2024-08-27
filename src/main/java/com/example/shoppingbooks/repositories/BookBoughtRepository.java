package com.example.shoppingbooks.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppingbooks.entities.BookBought;
import com.example.shoppingbooks.entities.Person;

import java.util.List;


@Repository
public interface BookBoughtRepository extends JpaRepository<BookBought,Long>{
Page<BookBought> findByPerson(Person person,Pageable pageable);
}
