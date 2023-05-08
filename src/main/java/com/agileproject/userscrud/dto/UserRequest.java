package com.agileproject.userscrud.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;



public record UserRequest(
        UUID id,
        String firstName,
        String lastName,
        String email,

        String password

) { //the requests that receives from the user


}
