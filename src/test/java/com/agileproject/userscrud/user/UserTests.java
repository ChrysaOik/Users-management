package com.agileproject.userscrud.user;


import com.agileproject.userscrud.entity.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
@SpringBootTest
public class UserTests { //checking if the validation annotations in user class work correctly


    @Test
    public void testFirstNameNotBlank() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = new User();
        user.setFirstName("");
        user.setLastName("Doe");
        user.setEmail("jdoe@example.com");
        user.setPassword("P4ssword!");


        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("First name is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    public void testFirstNameMaxSize() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = new User();
        user.setFirstName("FirstNameFirstNameFirstNameFirstNameFirstNameFirstNameFirstNameFirstNameFirstNameFirstNameFirstNameFirstName");
        user.setLastName("Doe");
        user.setEmail("jdoe@example.com");
        user.setPassword("P4ssword!");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("First name can be up to 100 characters long", violations.iterator().next().getMessage());
    }

    @Test
    public void testLastNameNotBlank() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = new User();
        user.setLastName("");
        user.setFirstName("John");
        user.setEmail("jdoe@example.com");
        user.setPassword("P4ssword!");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("Last name is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    public void testLastNameMaxSize() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = new User();
        user.setLastName("FirstNameFirstNameFirstNameFirstNameFirstNameFirstNameFirstNameFirstNameFirstNameFirstNameFirstNameFirstName");
        user.setFirstName("John");
        user.setEmail("jdoe@example.com");
        user.setPassword("P4ssword!");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("Last name can be up to 100 characters long", violations.iterator().next().getMessage());
    }

    @Test
    public void testEmailNotBlank() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = new User();
        user.setEmail("");
        user.setLastName("Doe");
        user.setFirstName("John");
        user.setPassword("P4ssword!");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("Email is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    public void testEmailMaxSize() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = new User();
        user.setEmail("firstkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk@gmail.com");
        user.setLastName("Doe");
        user.setFirstName("John");
        user.setPassword("P4ssword!");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("Email can be up to 50 characters long", violations.iterator().next().getMessage());
    }

    @Test
    public void testEmail() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = new User();
        user.setEmail("john.com");
        user.setLastName("Doe");
        user.setFirstName("John");
        user.setPassword("P4ssword!");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("Email should be valid", violations.iterator().next().getMessage());
    }

    @Test
    public void testPasswordNotBlank() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = new User();
        user.setPassword("");
        user.setEmail("jdoe@example.com");
        user.setLastName("Doe");
        user.setFirstName("John");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        //assertEquals(1, violations.size());
        assertEquals("Password is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    public void testPasswordPatter() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = new User();
        user.setPassword("abc");
        user.setEmail("jdoe@example.com");
        user.setLastName("Doe");
        user.setFirstName("John");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character and be at least 8 characters long", violations.iterator().next().getMessage());
    }

}
