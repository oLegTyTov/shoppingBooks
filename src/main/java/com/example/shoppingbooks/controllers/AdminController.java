package com.example.shoppingbooks.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.example.shoppingbooks.entities.Author;
import com.example.shoppingbooks.entities.Book;
import com.example.shoppingbooks.services.AuthorService;
import com.example.shoppingbooks.services.BookService;
import com.example.shoppingbooks.services.CategoryService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
@Secured("ADMIN")
public class AdminController {
    private AuthorService authorService;
    private BookService bookService;
    public AdminController(AuthorService authorService, BookService bookService){
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public String adminPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        return "html/adminPage";
    }

    @GetMapping("/addAuthor")
    public String addAuthorPage(@RequestParam(required = false) Boolean error, Model model) {
        if (error != null && error) {
            model.addAttribute("error", true);
        } else {
            model.addAttribute("error", false);
        }
        return "html/addAuthor";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute Author author, Model model, @RequestParam("photo") MultipartFile photo)
            throws IOException {
        if (!photo.isEmpty()) {
            byte[] photoBytes = photo.getBytes();
            // Збережіть фото у продукт
            author.setPhoto(photoBytes);
        }
        if (authorService.addAuthor(author)) {
            return "redirect:/admin?error=false";
        } else {
            return "redirect:/admin/addAuthor?error=true";
        }
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam(required = false) Boolean error, Model model) {
        if (error != null) {
            model.addAttribute("error", true);
        } else {
            model.addAttribute("error", false);
        }
        return "html/deleteBook";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam String title) {
        if (bookService.deleteBook(title)) {
            return "redirect:/admin";
        }
        return "redirect:/admin/deleteBook?error=true";
    }

    @GetMapping("/deleteAuthor")
    public String deleteAuthor(@RequestParam(required = false) Boolean error, Model model) {
        if (error != null) {
            model.addAttribute("error", true);
        } else {
            model.addAttribute("error", false);
        }
        return "html/deleteAuthor";
    }

    @PostMapping("/deleteAuthor")
    public String deleteAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        if (authorService.deleteAuthor(firstName, lastName)) {
            return "redirect:/admin";
        }
        return "redirect:/admin/deleteAuthor?error=true";
    }

    @GetMapping("/addBook")
    public String addBookPage(@RequestParam(required = false) Boolean error, Model model) {
        if (error != null) {
            model.addAttribute("error", true);
        } else {
            model.addAttribute("error", false);
        }
        return "html/addBook";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book, @RequestParam("photo") MultipartFile photo) throws IOException {
        if (!photo.isEmpty()) {
            byte[] photoBytes = photo.getBytes();
            // Збережіть фото у продукт
            book.setPhoto(photoBytes);
        }
        if (bookService.addBook(book)) {
            return "redirect:/admin";
        }
        return "redirect:/admin/addBook?error=true";
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
            throws ServletException {

        // Convert multipart object to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

    }

}
