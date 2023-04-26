package com.agileproject.userscrud.rest;

import com.agileproject.userscrud.dto.UserDTO;
import com.agileproject.userscrud.entity.User;
import com.agileproject.userscrud.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService; //

    public UserRestController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public Optional<UserDTO> getUser(@PathVariable UUID userId) {
        Optional<UserDTO> user = userService.findById(userId); //psaxnw ton xristi apo to id

        return user;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User newUser) {
        //newUser.setId(0); //thetw to id se 0 gia na ginei to create kai oxi update

        User dbUser = userService.save(newUser);
        return dbUser;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable UUID userId) {
        userService.deleteById(userId);

        return "Deleted user with id: " + userId;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        User dbUser = userService.save(user);

        return dbUser; //epistrefei ton kainourgio xristi-ananewmeno
    }


}
