package com.morsel.dao;

import com.morsel.models.FoodOrder;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class FoodOrderDao extends AbstractDAO<FoodOrder>{

    public FoodOrderDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
