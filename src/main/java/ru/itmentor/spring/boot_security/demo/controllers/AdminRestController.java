package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.services.UsersService;

@RestController
@RequestMapping("/api/users")
public class AdminRestController {
    private final UsersService usersService;
    @Autowired
    public AdminRestController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private void newUser(@RequestBody User newUser) {
        usersService.saveUser(newUser);
    }

    @PutMapping("/{id}")
    private ResponseEntity<HttpStatus> replace(@RequestBody User user, @PathVariable Long id) {
        usersService.updateUser(id, user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable Long id) {
        usersService.deleteUser(id);
    }
}
