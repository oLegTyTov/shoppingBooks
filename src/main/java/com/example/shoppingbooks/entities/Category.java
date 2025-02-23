package com.example.shoppingbooks.entities;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;


@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
        @Lob
    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo;
    @Transient
    private String base64Image;
    public Category()
    {
    }
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }public void setId(Long id) {
        this.id = id;
    }public void setName(String name) {
        this.name = name;
    }public void setPhoto(byte[] photo) {
        this.photo = photo;
    }public String getBase64Image() {
        return base64Image;
    }public Long getId() {
        return id;
    }public String getName() {
        return name;
    }public byte[] getPhoto() {
        return photo;
    }
}
