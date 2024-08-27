package com.example.shoppingbooks.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
public class Person {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false) 
    private Role role;
    private LocalDate dateCreationAccount=LocalDate.now();
    @OneToMany(mappedBy = "person",cascade = CascadeType.REMOVE)
    private List<BookBought> booksBoughts; // Книги, які були куплені
    private Integer numberOfBooksBought=0;
    private Double balance=100D;
}
