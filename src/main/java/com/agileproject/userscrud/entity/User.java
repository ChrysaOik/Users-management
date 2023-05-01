package com.agileproject.userscrud.entity;

import jakarta.persistence.*;
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
    private @Getter @Setter String firstName;
    @Column(name="last_name")
    private @Getter @Setter String lastName;
    @Column(name="email")
    private @Getter @Setter String email;
    @Column(name="password")
    private @Getter @Setter String password;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


}
