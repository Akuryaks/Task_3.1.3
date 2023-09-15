package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.services.UsersService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UsersService usersService;
    @Autowired
    public UserRestController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    private List<User> all(){
        return usersService.findAll();
    }

    @GetMapping("/{id}")
    private User findById(@PathVariable Long id){
        return usersService.findOne(id).orElseThrow(() -> new RuntimeException("Could not find user " + id));
    }
}
