package com.agileproject.userscrud.rest;

import com.agileproject.userscrud.dto.UserDTO;
import com.agileproject.userscrud.dto.UserRequest;
import com.agileproject.userscrud.dto.UserResponse;
import com.agileproject.userscrud.entity.User;
import com.agileproject.userscrud.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private UserService userService; //using service instead of dao

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Returns a list of all users")
    @GetMapping("")
    public ResponseEntity<List<UserResponse>> getAllUsers() { //customized HTTP response, including headers, status code and response body
        List<UserDTO> userDTOs= userService.findAll(); //returns a list of userDTOs from service layer
        List<UserResponse> userResponses = userDTOs.stream()
                .map(u -> new UserResponse(u.id(), u.firstName(), u.lastName(), u.email()))
                .collect(Collectors.toList()); //converting userDTOs to userResponse
        return ResponseEntity.ok(userResponses);
    }

    @Operation(summary = "Retrieves a user based on the provided userId")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID userId) {
        UserDTO user = userService.findById(userId); //psaxnw ton xristi apo to id
        UserResponse userResponse = new UserResponse(user.id(), user.firstName(), user.lastName(), user.email());

        return ResponseEntity.ok(userResponse);
    }

    @Operation(summary = "Adds a new user based on the provided UserRequest")
    @PostMapping("")
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserRequest userRequest) {
        UserDTO userDTO = userService.save(userRequest);
        UserResponse userResponse = new UserResponse(userDTO.id(), userDTO.firstName(), userDTO.lastName(), userDTO.email());
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse); //201 created
    }

    @Operation(summary = "Deletes a user based on the provided userId")
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable UUID userId) {
        userService.deleteById(userId);

        return "Deleted user with id: " + userId;
    }

    @Operation(summary = "Updates an existing user based on the provided UserRequest")
    @PutMapping("")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
        UserDTO userDTO = userService.update(userRequest);
        UserResponse userResponse = new UserResponse(userDTO.id(), userDTO.firstName(), userDTO.lastName(), userDTO.email());
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse); //epistrefei ton kainourgio xristi-ananewmeno
    }
}
