package com.amela.service;

import com.amela.model.User;
import com.amela.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository userRepository;




    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public User find(String username, String password) {

            return userRepository.find(username,password);

    }


}
