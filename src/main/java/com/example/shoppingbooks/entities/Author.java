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
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
import java.util.Objects;
@Entity
@Data
@NoArgsConstructor
public class Author {

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

    // Correct implementation of hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id); // Hash code based on id
    }
}
