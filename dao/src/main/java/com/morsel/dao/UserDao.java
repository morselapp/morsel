package com.morsel.dao;

import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by ajeet on 14/1/17.
 */
public class UserDao extends AbstractDAO<UserDao> {

    @Inject
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
