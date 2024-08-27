package com.example.shoppingbooks;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.shoppingbooks.entities.Book;
import com.example.shoppingbooks.entities.BookBought;
import com.example.shoppingbooks.entities.Category;
import com.example.shoppingbooks.entities.Person;
import com.example.shoppingbooks.entities.Role;
import com.example.shoppingbooks.services.AuthorService;
import com.example.shoppingbooks.services.BookBoughtService;
import com.example.shoppingbooks.services.BookService;
import com.example.shoppingbooks.services.PersonService;
@SpringBootApplication
public class ShoppingbooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingbooksApplication.class, args);
	}
    @Bean
    public CommandLineRunner commandLineRunner(BookService bookService,AuthorService authorService,PersonService personService,BookBoughtService bookBoughtService) {
        return args -> {
			/*Book book=new Book();
			book.setAuthor(authorService.findAuthorByNameAndSurname("Giorge", "Orwel"));
			book.setTitle("bober");
			book.setPrice(10.99);
			Category category=new Category();
			Category category2=new Category();
			category.setName("Comedy");
			category2.setName("Horror");
			book.setCategories(new HashSet<>(Arrays.asList(category, category2)));
			book.setPublicationDate(LocalDate.of(1945, 5, 7));
			bookService.addBook(book);*/
			personService.deletePerson(3L);
		};
}
}
