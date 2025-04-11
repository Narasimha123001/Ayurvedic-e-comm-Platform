package com.techtricks.ayurvedic.services;

import com.techtricks.ayurvedic.exceptions.UserAlreadyExistsException;
import com.techtricks.ayurvedic.exceptions.UserNotFoundException;
import com.techtricks.ayurvedic.models.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {


    public List<User> getAllUsers();

    public User saveUser(User user) throws UserAlreadyExistsException;

    public Optional<User> authenticate(String email, String password);

    public User  getUserByEmail(String email) throws UserNotFoundException;

   public User getUserByUsername(String username) throws UserNotFoundException;

   boolean deleteUser(String email) throws UserNotFoundException;

   public User updateUser(User user) throws UserNotFoundException;

}
