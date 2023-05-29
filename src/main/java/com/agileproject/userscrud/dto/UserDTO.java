package com.agileproject.userscrud.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


//I add only the fields that I want to display to the client
public record UserDTO( //focused on holding data, rather than behavior
        UUID id,
        String firstName,
        String lastName,
        String email
) { //the record automatically generates a constructor, getters, equals, hashCode and toString

}
