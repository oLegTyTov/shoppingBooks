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
@Entity
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
    public Book()
    {}
    public Author getAuthor() {
        return author;
    }public String getBase64Image() {
        return base64Image;
    }public List<BookBought> getBookBought() {
        return bookBought;
    }public Set<Category> getCategories() {
        return categories;
    }public LocalDate getCreationDate() {
        return creationDate;
    }public String getDescription() {
        return description;
    }public Long getId() {
        return id;
    }public byte[] getPhoto() {
        return photo;
    }public Double getPrice() {
        return price;
    }public LocalDate getPublicationDate() {
        return publicationDate;
    }public String getTitle() {
        return title;
    }public void setAuthor(Author author) {
        this.author = author;
    }public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }public void setBookBought(List<BookBought> bookBought) {
        this.bookBought = bookBought;
    }public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }public void setDescription(String description) {
        this.description = description;
    }public void setId(Long id) {
        this.id = id;
    }public void setPhoto(byte[] photo) {
        this.photo = photo;
    }public void setPrice(Double price) {
        this.price = price;
    }public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }public void setTitle(String title) {
        this.title = title;
    }
}
