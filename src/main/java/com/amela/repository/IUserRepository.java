package com.amela.repository;


import com.amela.model.User;

import java.util.List;

public interface IUserRepository  {
    User find(String username, String password);
    List<User> findAll();

}
