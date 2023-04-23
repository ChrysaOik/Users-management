package com.agileproject.userscrud.dao;

import com.agileproject.userscrud.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> theQuery = entityManager.createQuery("from User", User.class);

        List<User> userList = theQuery.getResultList();
        return userList;
    }

    @Override
    public User findById(int id) {
        User user = entityManager.find(User.class, id); //h find einai sinartisi tou entity manager

        return user;
    }

    @Transactional
    @Override
    public User save(User user) {
        User dbUser = entityManager.merge(user);

        return dbUser;
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

}
