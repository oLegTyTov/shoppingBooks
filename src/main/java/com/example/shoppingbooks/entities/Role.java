package com.example.shoppingbooks.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    public Role()
    {
    }
    public Long getId() {
        return id;
    }public String getName() {
        return name;
    }public void setId(Long id) {
        this.id = id;
    }public void setName(String name) {
        this.name = name;
    }
}
