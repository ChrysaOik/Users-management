package com.agileproject.userscrud.user;

import com.agileproject.userscrud.dto.UserDTO;
import com.agileproject.userscrud.dto.UserRequest;
import com.agileproject.userscrud.dto.UserResponse;
import com.agileproject.userscrud.rest.UserRestController;
import com.agileproject.userscrud.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock //creating a mock ?
    private UserService userService;

    @InjectMocks //inject the mock into the controller
    private UserRestController userController;

    @Test
    public void testGetAllUsers() {
        List<UserDTO> userDTOs = new ArrayList<>();
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        userDTOs.add(new UserDTO(uuid1, "Spiros", "Del", "spiros@gmail.com"));
        userDTOs.add(new UserDTO(uuid2, "Dalia", "Xatz", "dalia@gmail.com"));
        when(userService.findAll()).thenReturn(userDTOs);

        ResponseEntity<List<UserResponse>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<UserResponse> userResponses = response.getBody();
        assertEquals(2, userResponses.size());
        assertEquals(uuid1, userResponses.get(0).id());
        assertEquals("Spiros", userResponses.get(0).firstName());
        assertEquals("Del", userResponses.get(0).lastName());
        assertEquals("spiros@gmail.com", userResponses.get(0).email());
        assertEquals(uuid2, userResponses.get(1).id());
        assertEquals("Dalia", userResponses.get(1).firstName());
        assertEquals("Xatz", userResponses.get(1).lastName());
        assertEquals("dalia@gmail.com", userResponses.get(1).email());
    }

    @Test
    public void testGetUser() {
        UUID userId = UUID.randomUUID();
        String firstName = "Zoympi";
        String lastName = "Amp";
        String email = "zoumpoylia@gmail.com";

        UserDTO userDTO = new UserDTO(userId, firstName, lastName, email);

        when(userService.findById(userId)).thenReturn(userDTO);

        ResponseEntity<UserResponse> response = userController.getUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        UserResponse userResponse = response.getBody();

        assertEquals(userId, userResponse.id());
        assertEquals(firstName, userResponse.firstName());
        assertEquals(lastName, userResponse.lastName());
        assertEquals(email, userResponse.email());
    }

}