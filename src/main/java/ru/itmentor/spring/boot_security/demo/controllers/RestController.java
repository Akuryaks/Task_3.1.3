package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.services.UsersService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/users")
public class RestController {

    private final UsersService usersService;

    @Autowired
    public RestController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    private List<User> all(){
        return usersService.findAll();
    }

    @PostMapping
    private void newUser(@RequestBody User newUser) {
        usersService.saveUser(newUser);
    }

    @GetMapping("/{id}")
    private User findById(@PathVariable Long id){
        return usersService.findOne(id).orElseThrow(() -> new RuntimeException("Could not find user " + id));
    }

    @PutMapping("/{id}")
    private void replace(@RequestBody User user, @PathVariable Long id) {
        usersService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        usersService.deleteUser(id);
    }
}
