package com.example.shoppingbooks.controllers;

import java.util.Base64;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoppingbooks.entities.Book;
import com.example.shoppingbooks.entities.BookBought;
import com.example.shoppingbooks.entities.Person;
import com.example.shoppingbooks.services.BookBoughtService;
import com.example.shoppingbooks.services.PersonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Secured("USER")
public class UserController {
    private PersonService personService;
    private BookBoughtService bookBoughtService;

    public UserController(PersonService personService, BookBoughtService bookBoughtService) {
        this.personService = personService;
        this.bookBoughtService = bookBoughtService;
    }

    @GetMapping("/myAccount")
    public String myAccountPage(@AuthenticationPrincipal UserDetails userDetails,
            Model model) {
        Person person = personService.findByUsername(userDetails.getUsername());
        model.addAttribute("name", userDetails.getUsername());
        model.addAttribute("dateCreationAccount", person.getDateCreationAccount());
        model.addAttribute("numberOfBooksBought", person.getNumberOfBooksBought());
        model.addAttribute("balance", person.getBalance());
        return "html/myAccount.html";
    }

    @GetMapping("/buyBook")
    public String buyBook(@RequestParam Long id) {

        if (bookBoughtService.buyBook(id)) {
            return "redirect:/myAccount";
        } else {
            return "redirect:/getBook?id=" + id + "&error=true";// або книжка вже не існує або в юзера немає балансу
        }
    }

    @GetMapping("/getAllBoughtBooks")
    public String getAllBoughtBooks(Model model, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = MainController.sizePagination) int size) {
        Page<BookBought> pageBookBoughts = bookBoughtService.getAllBookBought(page, size);
        pageBookBoughts.getContent().forEach(bookBought -> {
            String base64Image = Base64.getEncoder().encodeToString(bookBought.getBook().getPhoto());
            bookBought.getBook().setBase64Image(base64Image);
        });
        model.addAttribute("pageBookBoughts", pageBookBoughts);
        return "html/getAllBoughtBooks";
    }

    @GetMapping("/addBalance")
    public String addBalance() {
        return "html/addBalance";
    }

    @PostMapping("/addBalance")
    public String addBalance(@RequestParam Double balance) {
        if (balance < 0) {
            System.out.println("error");
            return "redirect:/addBalance";
        } else {
            personService.addBalance(balance);
            return "redirect:/myAccount";
        }
    }

}
