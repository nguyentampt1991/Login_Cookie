package com.amela.service;

import com.amela.model.User;

import java.util.List;
public interface IUserService {

    List<User> findAll();

     User find(String username, String password) ;


}
