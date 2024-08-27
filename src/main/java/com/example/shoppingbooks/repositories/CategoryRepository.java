package com.example.shoppingbooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppingbooks.entities.Category;
import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
Category findByName(String name);
}
