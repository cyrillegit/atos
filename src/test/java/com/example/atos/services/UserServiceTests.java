package com.example.atos.services;

import com.example.atos.exceptions.AtosException;
import com.example.atos.models.User;
import com.example.atos.repositories.UserRepository;
import com.example.atos.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserServiceTests {
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);

    public UserService userService;

    @BeforeEach
    public void setup(){
        userService = new UserServiceImpl(userRepository);
    }

    private User generateUser(String username, LocalDate birthdate, String country){
        User user = new User();
        user.setUsername(username);
        user.setBirthdate(birthdate);
        user.setCountry(country);
        return user;
    }

    @Test
    @DisplayName("Should register user")
    public void shouldRegisterUser() throws AtosException {
        User userToRegister = generateUser("username", LocalDate.of(2000, 02, 25), "FRANCE");
        User userExpected = userToRegister;
        userExpected.setId(12);
        Mockito.when(userRepository.save(userToRegister)).thenReturn(userExpected);

        User user = userService.register(userToRegister);

        assertThat(user.getId()).isEqualTo(12);
        assertThat(user.getUsername()).isEqualTo(userToRegister.getUsername());
        assertThat(user.getBirthdate()).isEqualTo(userToRegister.getBirthdate());
        assertThat(user.getCountry()).isEqualTo(userToRegister.getCountry());
        assertThat(user.getGender()).isNull();
        assertThat(user.getPhoneNumber()).isNull();
    }

    @Test
    @DisplayName("Should not register user because already in database")
    public void shouldNotRegisterUserAlreadyInDatabase() {
        User userToRegister = generateUser("username", LocalDate.of(2000, 02, 25), "FRANCE");
        User userExpected = userToRegister;
        userExpected.setId(12);
        Mockito.when(userRepository.findByUsername(userToRegister.getUsername())).thenReturn(Optional.of(userExpected));

        assertThatThrownBy(() -> userService.register(userToRegister))
                .isInstanceOf(AtosException.class)
                .hasMessage("User already in the database");
    }

    @Test
    @DisplayName("Should display user details")
    public void shouldDisplayUserDetails() throws AtosException {
        User userRegistered = generateUser("username", LocalDate.of(2000, 02, 25), "FRANCE");
        userRegistered.setId(21);
        Mockito.when(userRepository.findById(21)).thenReturn(Optional.of(userRegistered));

        User user = userService.display(21);

        assertThat(user.getId()).isEqualTo(21);
        assertThat(user.getUsername()).isEqualTo(userRegistered.getUsername());
        assertThat(user.getBirthdate()).isEqualTo(userRegistered.getBirthdate());
        assertThat(user.getCountry()).isEqualTo(userRegistered.getCountry());
        assertThat(user.getGender()).isNull();
        assertThat(user.getPhoneNumber()).isNull();
    }

    @Test
    @DisplayName("Should not display user details because not in database")
    public void shouldNotDisplayUserDetails() {
        User user = generateUser("username", LocalDate.of(2000, 02, 25), "FRANCE");
        user.setId(12);
        Mockito.when(userRepository.findById(14)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.display(14))
                .isInstanceOf(AtosException.class)
                .hasMessage("User does not exist");
    }
}
