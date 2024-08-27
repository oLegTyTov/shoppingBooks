package com.example.shoppingbooks.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.shoppingbooks.entities.Author;
import com.example.shoppingbooks.entities.Book;
import com.example.shoppingbooks.entities.Category;
import com.example.shoppingbooks.repositories.BookRepository;
import java.util.*;

@Service
public class BookService {
    private BookRepository bookRepository;
    private CategoryService categoryService;
    private AuthorService authorService;

    public BookService(BookRepository bookRepository, CategoryService categoryService, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public boolean addBook(Book book) {
        if (book == null) {
            System.out.println("errorNullBook");
        } else if (bookRepository.existsByTitle(book.getTitle())) {
            System.out.println("errorExistBookAddBook");
        } else if (!authorService.existsByFirstNameAndLastName(book.getAuthor().getFirstName(),
                book.getAuthor().getLastName())) {
            System.out.println("nonexistAuthoraddBook");
        } else {
            Set<Category> categories = new HashSet();
            for (Category category : book.getCategories()) {
                categories.add(categoryService.findCategoryByName(category.getName()));
            }
            Author author = authorService.findAuthorByNameAndSurname(book.getAuthor().getFirstName(),
                    book.getAuthor().getLastName());
            book.setCategories(categories);
            Integer currentNumBooks = author.getNumberBooks() + 1;
            author.setNumberBooks(currentNumBooks);
            book.setAuthor(author);
            authorService.updateAuthor(author);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    public Book findById(Long id) {
        if (bookRepository.existsById(id)) {
            return bookRepository.findById(id).get();

        }
        return null;
    }

    public boolean deleteBook(String title) {
        if (bookRepository.existsByTitle(title)) {
            Book book = bookRepository.findByTitle(title);
            bookRepository.deleteBookCategoryByBookId(book.getId());// чистимо табличку book_category
            bookRepository.deleteById(book.getId());
            return true;
        } else {
            return false;
        }
    }

    public Page<Book> getAllBooks(int page, int size, String nameBookLike) {
        Specification<Book> specification = Specification.where(null);
        if (nameBookLike != null) {
            specification = specification
                    .and((root, query, cb) -> cb.like(root.get("title"), "%" + nameBookLike + "%"));
        }
        return bookRepository.findAll(specification, PageRequest.of(page, size));
    }

    public Page<Book> getBooksOfCategory(Long id, int page, int size) {
        return bookRepository.findAllByCategoryId(id, PageRequest.of(page, size));
    }

    public Page<Book> getBooksOfAuthor(Long id, int page, int size) {
        Author author = authorService.findAuthor(id);
        return bookRepository.findByAuthor(author, PageRequest.of(page, size));
    }
}
