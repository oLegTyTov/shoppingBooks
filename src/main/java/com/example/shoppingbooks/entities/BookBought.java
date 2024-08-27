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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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
    
}
