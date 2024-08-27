package com.example.shoppingbooks.services;

import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shoppingbooks.entities.Person;
import com.example.shoppingbooks.repositories.PersonRepository;
import com.example.shoppingbooks.repositories.RoleRepository;

@Service
public class PersonService implements UserDetailsService {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder; // Додаємо PasswordEncoder

    public PersonService(PersonRepository personRepository, RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder; // Ініціалізуємо PasswordEncoder
    }

    public void updatePerson(Person person) {
        personRepository.save(person);
    }
    public boolean addPerson(Person person) {
        if (personRepository.existsByUsername(person.getUsername())) {
            return false;
        }
        person.setRole(roleRepository.findByName(person.getRole().getName()));

        // Хешуємо пароль перед збереженням
        String encodedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encodedPassword);

        personRepository.save(person);
        return true;
    }

    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Додаємо роль користувача як GrantedAuthority
        GrantedAuthority authority = new SimpleGrantedAuthority(person.getRole().getName());
        return new org.springframework.security.core.userdetails.User(
                person.getUsername(),
                person.getPassword(),
                Collections.singletonList(authority));
    }

    public void addBalance(Double balance) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;// downcasting
        Person person = findByUsername(userDetails.getUsername());
        Double currentBalance=person.getBalance()+balance;
        person.setBalance(currentBalance);
        updatePerson(person);
    }
}
