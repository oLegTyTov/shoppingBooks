package com.example.shoppingbooks.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Flow.Publisher;

import java.util.Objects;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private Double price;
    @Lob
    @Column(name = "photo", columnDefinition = "LONGBLOB",nullable = false)
    private byte[] photo;
    private String description;
    @Transient
    private String base64Image;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @Column(name = "publication_date")
    private LocalDate publicationDate;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @OneToMany(mappedBy = "book",cascade = CascadeType.REMOVE)
    private List<BookBought> bookBought;
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book other = (Book) obj;
        return Objects.equals(id, other.id); // Use id for comparison
    }
    @Override
    public int hashCode() {
        return Objects.hash(id); // Hash code based on id
    }
}
