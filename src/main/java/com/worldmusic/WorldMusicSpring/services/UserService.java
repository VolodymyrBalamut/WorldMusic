package com.worldmusic.WorldMusicSpring.services;

import com.worldmusic.WorldMusicSpring.model.Style;
import com.worldmusic.WorldMusicSpring.model.User;
import com.worldmusic.WorldMusicSpring.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
    //public boolean validateUser(String email, String password);



    public List<User> getAllUsers();
    public User getUser(int id);

    public User addUser(User user);
    public User updateUser(User user);
    public boolean deleteUser(int id);
}
