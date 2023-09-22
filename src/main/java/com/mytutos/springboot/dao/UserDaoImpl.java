package com.mytutos.springboot.dao;

import com.mytutos.springboot.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByUserName(String theUserName) {

        //retrieve/read from the DB
        TypedQuery<User> theQuery =
                entityManager.createQuery("from User where userName =:uName", User.class);
        theQuery.setParameter("uName", theUserName);

        User theUser;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;
    }

    @Override
    @Transactional
    public void save(User theUser) {
        // create the user ...
        entityManager.merge(theUser);

    }
}
