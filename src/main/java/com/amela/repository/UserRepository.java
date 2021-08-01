package com.amela.repository;

import com.amela.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserRepository implements IUserRepository{
    @Autowired
    private EntityManager em;
    @Override
    public User find(String username, String password) {
        TypedQuery query = em.createNamedQuery("userByUserNameAndPass",User.class);
        query.setParameter("username",username);
        query.setParameter("password",password);
        User user = (User) query.getSingleResult();
        return user;
    }

    @Override
    public List<User> findAll() {
        TypedQuery query= em.createNamedQuery("listUser",User.class);
        return query.getResultList();
    }
}
