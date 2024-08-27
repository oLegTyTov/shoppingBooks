package com.example.shoppingbooks.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.shoppingbooks.entities.Author;
import com.example.shoppingbooks.entities.Book;

import jakarta.transaction.Transactional;
import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Long>,JpaSpecificationExecutor<Book>{
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM book_category WHERE book_id = :bookId", nativeQuery = true)
    void deleteBookCategoryByBookId(@Param("bookId") Long bookId);
    Book findByTitle(String title);
    boolean existsByTitle(String title);
    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.id = :categoryId")
    Page<Book> findAllByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);
    Page<Book> findByAuthor(Author author,Pageable pageable);
}
