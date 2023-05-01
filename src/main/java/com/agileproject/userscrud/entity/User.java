package com.agileproject.userscrud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name="id")
    private @Getter @Setter UUID id;
    @Column(name="first_name")
    @NotBlank(message = "First name is mandatory")
    @Size(max = 100, message = "First name can be up to 100 characters long")
    private @Getter @Setter String firstName;
    @Column(name="last_name")
    @NotBlank(message = "Last name is mandatory")
    @Size(max = 100, message = "Last name can be up to 100 characters long")
    private @Getter @Setter String lastName;
    @Column(name="email")
    @Size(max = 100, message = "Email can be up to 100 characters long")
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")

    private @Getter @Setter String email;
    @Column(name="password")
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$",
            message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character and be at least 8 characters long")
    private @Getter @Setter String password;


    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


}
