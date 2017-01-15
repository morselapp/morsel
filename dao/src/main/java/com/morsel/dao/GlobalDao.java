package com.morsel.dao;

import com.morsel.models.Model;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ajeet on 14/1/17.
 */
public class GlobalDao<Model> extends AbstractDAO<Model> {
    private SessionFactory sessionFactory;
    public GlobalDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public Model getById(@NotNull int id){
        return get(id);
    }

    public Serializable add(@NotNull Model model){
        return sessionFactory.getCurrentSession().save(model);
    }
}
