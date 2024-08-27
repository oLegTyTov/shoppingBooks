package com.example.shoppingbooks.services;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.shoppingbooks.entities.Author;
import com.example.shoppingbooks.entities.Book;
import com.example.shoppingbooks.repositories.AuthorRepository;
import com.example.shoppingbooks.repositories.BookRepository;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author findAuthorByNameAndSurname(String name, String surname) {
        return authorRepository.findByFirstNameAndLastName(name, surname);
    }

    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).get();
    }

    public boolean addAuthor(Author author) {
        if (author == null) {
            System.out.println("authorNullAddAthore");
            return false;
        } else if (authorRepository.existsByFirstNameAndLastName(author.getFirstName(), author.getLastName())) {
            System.out.println("sameAuthorAddAuthor");
            return false;
        } else {
            authorRepository.save(author);
            return true;
        }
    }
    public void updateAuthor(Author author)
    {
    authorRepository.save(author);
    }
    public boolean existsByFirstNameAndLastName(String firstName, String lastName) {
        if (authorRepository.existsByFirstNameAndLastName(firstName, lastName)) {
            return true;
        } else {
            return false;
        }
    }

    public Author findAuthor(Long id) {
        return authorRepository.findById(id).get();
    }

    public boolean deleteAuthor(String firstName, String lastName) {
        if (authorRepository.existsByFirstNameAndLastName(firstName, lastName)) {
            Author author = authorRepository.findByFirstNameAndLastName(firstName, lastName);
            if (author.getBooks() != null) {
                if (!author.getBooks().isEmpty()) {
                    for (Book book : author.getBooks())// чистимо табличку book_category перед видаленням автора,бо
                                                       // зв'язок
                    // ManyToMany
                    {
                        bookRepository.deleteBookCategoryByBookId(book.getId());
                    }
                }
            }
            authorRepository.deleteById(author.getId());
            return true;
        }
        return false;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
