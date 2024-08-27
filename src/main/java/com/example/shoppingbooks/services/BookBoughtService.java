package com.example.shoppingbooks.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.shoppingbooks.entities.Author;
import com.example.shoppingbooks.entities.Book;
import com.example.shoppingbooks.entities.BookBought;
import com.example.shoppingbooks.entities.Person;
import com.example.shoppingbooks.repositories.BookBoughtRepository;

@Service
public class BookBoughtService {
    private BookBoughtRepository bookBoughtRepository;
    private BookService bookService;
    private PersonService personService;

    public BookBoughtService(PersonService personService, BookBoughtRepository bookBoughtRepository,
            BookService bookService) {
        this.bookBoughtRepository = bookBoughtRepository;
        this.bookService = bookService;
        this.personService = personService;
    }

    public void addBookBought(BookBought bookBought) {
        bookBought.setBook(bookService.findByTitle(bookBought.getBook().getTitle()));
        bookBought.setPerson(personService.findByUsername(bookBought.getPerson().getUsername()));
        bookBoughtRepository.save(bookBought);
    }

    public boolean buyBook(Long id) {
        BookBought bookBought = new BookBought();
        Book book = bookService.findById(id);
        if (book != null) {
            bookBought.setBook(book);
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetails userDetails = (UserDetails) principal;// downcasting
            Person person = personService.findByUsername(userDetails.getUsername());
            if (person.getBalance() < book.getPrice()) {
                return false;// не хватає грошей
            }
            bookBought.setPerson(person);
            bookBoughtRepository.save(bookBought);
            person.setBalance(person.getBalance() - book.getPrice());// віднімаю баланс в юзера перед покупкою
            Integer currentnumBooks = person.getNumberOfBooksBought() + 1;
            person.setNumberOfBooksBought(currentnumBooks);
            personService.updatePerson(person);// оновлюю юзера
            return true;
        }
        return false;
    }

    public Page<BookBought> getAllBookBought(int page, int size) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;// downcasting
        Person person = personService.findByUsername(userDetails.getUsername());
        return bookBoughtRepository.findByPerson(person, PageRequest.of(page, size));
    }
}
