package com.agileproject.userscrud.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class UserErrorResponse {

    private @Getter @Setter int status;
    private @Getter @Setter String message;
    private @Getter @Setter long timeStamp;



}
