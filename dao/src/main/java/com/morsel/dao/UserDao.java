package com.morsel.dao;

import com.morsel.models.Model;
import com.google.inject.Inject;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.SessionFactory;

import javax.activation.DataSource;

/**
 * Created by ajeet on 14/1/17.
 */
public class UserDao extends GlobalDao<Model> {

    @Inject
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
