package com.worldmusic.WorldMusicSpring.services;

import com.worldmusic.WorldMusicSpring.model.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
    //public boolean validateUser(String email, String password);
}
