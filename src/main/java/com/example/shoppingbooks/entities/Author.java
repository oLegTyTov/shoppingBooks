package com.example.shoppingbooks.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

import java.util.Set;
import java.util.Objects;
@Entity

public class Author {
public Author()
{

}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String biography;
    private String nationality;
    @Lob
    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo;
    @Transient
    private String base64Image;
    @Column(name = "number_books")
    private Integer numberBooks=0;
    @OneToMany(mappedBy = "author",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private Set<Book> books;
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Author other = (Author) obj;
        return Objects.equals(id, other.id); // Correctly comparing ids
    }
public String getBase64Image() {
    return base64Image;
}public String getBiography() {
    return biography;
}public Set<Book> getBooks() {
    return books;
}public String getFirstName() {
    return firstName;
}public Long getId() {
    return id;
}public String getLastName() {
    return lastName;
}public String getNationality() {
    return nationality;
}public Integer getNumberBooks() {
    return numberBooks;
}public byte[] getPhoto() {
    return photo;
}public void setBase64Image(String base64Image) {
    this.base64Image = base64Image;
}public void setBiography(String biography) {
    this.biography = biography;
}public void setBooks(Set<Book> books) {
    this.books = books;
}public void setFirstName(String firstName) {
    this.firstName = firstName;
}public void setId(Long id) {
    this.id = id;
}public void setLastName(String lastName) {
    this.lastName = lastName;
}public void setNationality(String nationality) {
    this.nationality = nationality;
}public void setNumberBooks(Integer numberBooks) {
    this.numberBooks = numberBooks;
}public void setPhoto(byte[] photo) {
    this.photo = photo;
}
    // Correct implementation of hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id); // Hash code based on id
    }
}
