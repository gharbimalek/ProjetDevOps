package com.example.projetmpisi.service.imp;

import com.example.projetmpisi.entity.User;
import com.example.projetmpisi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testSaveUser() {
        User user = new User(1, "Bilel", "bilel@example.com");
        when(userRepository.save(user)).thenReturn(user);
        User saved = userService.saveUser(user);
        assertEquals("Bilel", saved.getUsername());
    }

    @Test
    void testGetUserById() {
        User user = new User(1, "Bilel", "bilel@example.com");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        Optional<User> result = userService.getUserById(1);
        assertTrue(result.isPresent());
        assertEquals("bilel@example.com", result.get().getEmail());
    }

    @Test
    void testUpdateUser_Success() {
        User existingUser = new User(1, "Old", "old@test.com");
        when(userRepository.existsById(1)).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(existingUser);
        
        User result = userService.updateUser(1, existingUser);
        assertNotNull(result);
        verify(userRepository).save(existingUser);
    }

    @Test
    void testUpdateUser_NotFound() {
        when(userRepository.existsById(99)).thenReturn(false);
        User result = userService.updateUser(99, new User());
        assertNull(result);
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1);
        verify(userRepository, times(1)).deleteById(1);
    }
}