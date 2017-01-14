package com.morsel.dao;

import com.morsel.models.Model;
import com.morsel.models.User;
import com.sun.jersey.api.core.InjectParam;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by ajeet on 14/1/17.
 */
public class UserDao extends GlobalDao<Model> {

    public UserDao(@InjectParam SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
