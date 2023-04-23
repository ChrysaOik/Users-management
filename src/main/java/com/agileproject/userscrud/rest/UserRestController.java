package com.agileproject.userscrud.rest;

import com.agileproject.userscrud.dao.UserDAO;
import com.agileproject.userscrud.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserDAO userDAO; //

    public UserRestController(UserDAO theUserDAO) {
        userDAO = theUserDAO;
    }
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {
        User user = userDAO.findById(userId); //psaxnw ton xristi apo to id

        return user;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User newUser) {
        newUser.setId(0); //thetw to id se 0 gia na ginei to create kai oxi update

        User dbUser = userDAO.save(newUser);
        return dbUser;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId) {
        userDAO.deleteById(userId);

        return "Deleted user with id: " + userId;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        User dbUser = userDAO.save(user);

        return dbUser; //epistrefei ton kainourgio xristi-ananewmeno
    }

}
