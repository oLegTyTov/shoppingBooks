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

@Entity

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
    public Person()
    {
    }
    public Double getBalance() {
        return balance;
    }public List<BookBought> getBooksBoughts() {
        return booksBoughts;
    }public LocalDate getDateCreationAccount() {
        return dateCreationAccount;
    }public Long getId() {
        return id;
    }public Integer getNumberOfBooksBought() {
        return numberOfBooksBought;
    }public String getPassword() {
        return password;
    }public Role getRole() {
        return role;
    }public String getUsername() {
        return username;
    }public void setBalance(Double balance) {
        this.balance = balance;
    }public void setBooksBoughts(List<BookBought> booksBoughts) {
        this.booksBoughts = booksBoughts;
    }public void setDateCreationAccount(LocalDate dateCreationAccount) {
        this.dateCreationAccount = dateCreationAccount;
    }public void setId(Long id) {
        this.id = id;
    }public void setNumberOfBooksBought(Integer numberOfBooksBought) {
        this.numberOfBooksBought = numberOfBooksBought;
    }public void setPassword(String password) {
        this.password = password;
    }public void setRole(Role role) {
        this.role = role;
    }public void setUsername(String username) {
        this.username = username;
    }
}
