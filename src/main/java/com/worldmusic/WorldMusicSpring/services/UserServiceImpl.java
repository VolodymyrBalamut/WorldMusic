package com.worldmusic.WorldMusicSpring.services;

import java.util.Arrays;
import java.util.HashSet;

import com.worldmusic.WorldMusicSpring.model.Role;
import com.worldmusic.WorldMusicSpring.model.User;
import com.worldmusic.WorldMusicSpring.repositories.RoleRepository;
import com.worldmusic.WorldMusicSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    /*@Override
    public boolean validateUser(String email, String password){
        if(findUserByEmail(email)!=null){
            User user = findUserByEmail(email);
            if(user.getPassword() == bCryptPasswordEncoder.
                    encode(bCryptPasswordEncoder.encode(password))){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }*/

}
