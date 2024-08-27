package com.example.shoppingbooks.controllers;

import java.util.Base64;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoppingbooks.entities.Author;
import com.example.shoppingbooks.entities.Book;
import com.example.shoppingbooks.entities.Category;
import com.example.shoppingbooks.entities.Person;
import com.example.shoppingbooks.repositories.CategoryRepository;
import com.example.shoppingbooks.services.AuthorService;
import com.example.shoppingbooks.services.BookService;
import com.example.shoppingbooks.services.PersonService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {// mainPage+pages that ate connected with authentication
    private PersonService personService;
    private BookService bookService;
    private CategoryRepository categoryRepository;
    private AuthorService authorService;
    public static final String sizePagination = "6";

    public MainController(PersonService personService, BookService bookService,CategoryRepository categoryRepository,AuthorService authorService) {
        this.personService = personService;
        this.bookService = bookService;
        this.categoryRepository=categoryRepository;
        this.authorService=authorService;
    }
    @GetMapping("/about")
    public String about() {
        return "html/aboutUs";
    }
    @GetMapping("/contact")
    public String contact() {
        return "html/contactUs";
    }
    
    
    @GetMapping
    public String mainPage(@AuthenticationPrincipal UserDetails userDetails,
            Model model,
            @RequestParam(required = false) Boolean autenticated,
            @RequestParam(required = false) String nameBook,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = sizePagination) int size) {
        if (userDetails != null) {
            model.addAttribute("userDetails", userDetails);
            model.addAttribute("role",userDetails.getAuthorities().iterator().next().getAuthority());
            model.addAttribute("autenticated", autenticated);
        }
        Page<Book> booksPage = bookService.getAllBooks(page, size, nameBook);
        booksPage.getContent().forEach(book -> {
            String base64Image = Base64.getEncoder().encodeToString(book.getPhoto());
            book.setBase64Image(base64Image);
        });
        model.addAttribute("nameBook",nameBook);
        model.addAttribute("booksPage", booksPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", booksPage.getTotalPages());
        return "html/mainPage";
    }
    @GetMapping("/getBook")
    public String getBookPage(@RequestParam Long id,Model model,@RequestParam(required = false) Boolean error) {
        Book book=bookService.findById(id);
        String base64Image = Base64.getEncoder().encodeToString(book.getPhoto());
        book.setBase64Image(base64Image);
        if(error!=null)
        {
        model.addAttribute("error",true);
        }
        Hibernate.initialize(book.getCategories());
        model.addAttribute("book", book);
        model.addAttribute("bookId",id);
        return "html/getBook";
    }
    
    @GetMapping("/signup")
    public String signupPage(Model model, @RequestParam(required = false) Boolean error) {
        if (error != null && error) {
            model.addAttribute("error", true);
        } else {
            model.addAttribute("error", false);
        }
        return "html/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Person person) {
        if (personService.addPerson(person)) {
            return "redirect:/login";
        } else {
            return "redirect:/signup?error=true";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) Boolean error, Model model) {
        if (error != null && error) {
            model.addAttribute("error", true);
        } else {
            model.addAttribute("error", false);
        }
        return "html/login";
    }
    @GetMapping("/getCategories")
    public String getCategories(Model model) {
        List<Category>categories=categoryRepository.findAll();
        categories.forEach(category -> {
            String base64Image = Base64.getEncoder().encodeToString(category.getPhoto());
            category.setBase64Image(base64Image);
        });
        model.addAttribute("categories", categories);
        return "html/getCategories";
    }
    @GetMapping("/getBooksOfCategory")
    public String getBooksOfCategory(@RequestParam Long id,Model model,@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = sizePagination) int size) {
        Page<Book>pageBooks=bookService.getBooksOfCategory(id,page,size);
        pageBooks.getContent().forEach(book -> {
            String base64Image = Base64.getEncoder().encodeToString(book.getPhoto());
            book.setBase64Image(base64Image);
        });
        model.addAttribute("pageBooks",pageBooks);
        model.addAttribute("categoryId", id);
        return "html/getBooksOfCategory";
    }
    @GetMapping("/getAuthors")
    public String getAuthors(Model model) {
        List<Author>authors=authorService.getAllAuthors();
        authors.forEach(author -> {
            String base64Image = Base64.getEncoder().encodeToString(author.getPhoto());
            author.setBase64Image(base64Image);
        });
        model.addAttribute("authors", authors);
        return "html/getAuthors";
    }
    @GetMapping("/getAuthor")
    public String getAuthor(@RequestParam Long id,Model model) {
        Author author=authorService.findAuthor(id);
        String base64Image = Base64.getEncoder().encodeToString(author.getPhoto());
        author.setBase64Image(base64Image);
        model.addAttribute("author", author);
        return "html/getAuthor";
    }
    
    @GetMapping("/getBooksOfAuthor")
    public String getBooksOfAuthor(@RequestParam Long id, Model model, 
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = sizePagination) int size) {
        Page<Book> pageBooks = bookService.getBooksOfAuthor(id, page, size);
        pageBooks.getContent().forEach(book -> {
            String base64Image = Base64.getEncoder().encodeToString(book.getPhoto());
            book.setBase64Image(base64Image);
        });
        model.addAttribute("authorId", id);
        String nameAuthor="";
        if(!pageBooks.isEmpty())
    {
    nameAuthor=pageBooks.getContent().get(0).getAuthor().getFirstName()+" "+pageBooks.getContent().get(0).getAuthor().getLastName();
    }
        model.addAttribute("nameAuthor",nameAuthor);
        model.addAttribute("pageBooks", pageBooks);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBooks.getTotalPages());
        model.addAttribute("size", size);
        return "html/getBooksOfAuthor";
    }
    
}
