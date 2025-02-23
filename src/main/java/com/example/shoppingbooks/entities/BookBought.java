package com.example.shoppingbooks.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity

@Table(name = "bookbought")
public class BookBought{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
        @Column(name = "date_bought")
    private LocalDateTime dateBought=LocalDateTime.now();//дата покупки
    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;
    @ManyToOne
    @JoinColumn(name = "person_id",nullable = false)
    private Person person;
    public BookBought()
    {}
    public Book getBook() {
        return book;
    }public LocalDateTime getDateBought() {
        return dateBought;
    }public Long getId() {
        return id;
    }public Person getPerson() {
        return person;
    }
    public void setBook(Book book) {
        this.book = book;
    }public void setDateBought(LocalDateTime dateBought) {
        this.dateBought = dateBought;
    }public void setId(Long id) {
        this.id = id;
    }public void setPerson(Person person) {
        this.person = person;
    }
}
