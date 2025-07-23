package com.memorygame.service;

import com.memorygame.model.Role;
import com.memorygame.model.User;
import com.memorygame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean register(User user) {
      if (userRepository.existsByUsername(user.getUsername())) return false;
      user.setRole(Role.PLAYER);
      userRepository.save(user);
      return true;
  }
  
  public User validateLogin(String username, String password) {
      User user = userRepository.findByUsername(username);
      if (user == null || !user.getPassword().equals(password)) return null;
      return user;
  }  

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
