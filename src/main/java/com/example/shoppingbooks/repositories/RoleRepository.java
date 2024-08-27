package com.example.shoppingbooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppingbooks.entities.Role;
import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
Role findByName(String name);
}
