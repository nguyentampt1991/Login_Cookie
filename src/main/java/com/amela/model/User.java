package com.amela.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@NamedQuery(name = "userByUserNameAndPass",
        query = "select u from User u where u.username= :username and u.password = :password")
@NamedQuery(name = "listUser",
        query = "select u from User u ")
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @NotEmpty
    @Size(min = 4,max = 14)
    private String username;
    @NotEmpty
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
