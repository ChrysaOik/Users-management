package com.agileproject.userscrud.user;

import com.agileproject.userscrud.dao.UserRepository;
import com.agileproject.userscrud.dto.UserDTO;
import com.agileproject.userscrud.dto.UserRequest;
import com.agileproject.userscrud.entity.User;
import com.agileproject.userscrud.rest.UserNotFoundException;
import com.agileproject.userscrud.service.UserService;
import com.agileproject.userscrud.service.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class) //initializes the class and its dependencies for mocking
public class UserServiceTest {

    @Mock
    private UserRepository userRepository; //create a mock object, stimulates the behavior of the object without having to create a real object

    @InjectMocks
    private UserServiceImpl userServiceImpl; //inject mocks in UserServiceImpl

    @Test
    public void testFindAll() {
        User user1 = new User();
        UUID uuid1 = UUID.randomUUID();
        user1.setId(uuid1);
        user1.setFirstName("Mary");
        user1.setLastName("Pop");
        user1.setEmail("mary@gmail.com");

        User user2 = new User();
        UUID uuid2 = UUID.randomUUID();
        user2.setId(uuid2);
        user2.setFirstName("Micky");
        user2.setLastName("Mouse");
        user2.setEmail("micky@gmail.com");

        List<User> userList = Arrays.asList(user1, user2);

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        List<UserDTO> result = userServiceImpl.findAll();

        assertEquals(userList.size(), result.size());
        assertEquals(userList.get(0).getId(), result.get(0).id());
        assertEquals(userList.get(0).getFirstName(), result.get(0).firstName());
        assertEquals(userList.get(0).getLastName(), result.get(0).lastName());
        assertEquals(userList.get(0).getEmail(), result.get(0).email());
        assertEquals(userList.get(1).getId(), result.get(1).id());
        assertEquals(userList.get(1).getFirstName(), result.get(1).firstName());
        assertEquals(userList.get(1).getLastName(), result.get(1).lastName());
        assertEquals(userList.get(1).getEmail(), result.get(1).email());
    }

    @Test
    public void testFindById() throws UserNotFoundException {
        User user = new User();
        UUID uuid1 = UUID.randomUUID();
        user.setId(uuid1);
        user.setFirstName("Scooby");
        user.setLastName("Doo");
        user.setEmail("scooby@yahoo.com");

        Mockito.when(userRepository.findById(uuid1)).thenReturn(Optional.of(user));

        UserDTO result = userServiceImpl.findById(uuid1);

        assertEquals(user.getId(), result.id());
        assertEquals(user.getFirstName(), result.firstName());
        assertEquals(user.getLastName(), result.lastName());
        assertEquals(user.getEmail(), result.email());
    }


    @Test
    public void testSave() {
        UserRequest userRequest = new UserRequest(UUID.randomUUID(), "onoma", "epitheto", "email@e.com", "password");

        User user = new User();
        UUID uuid1 = UUID.randomUUID();
        user.setId(uuid1);
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserDTO result = userServiceImpl.save(userRequest);

        assertEquals(user.getId(), result.id());
        assertEquals(user.getFirstName(), result.firstName());
        assertEquals(user.getLastName(), result.lastName());
        assertEquals(user.getEmail(), result.email());
    }
}
