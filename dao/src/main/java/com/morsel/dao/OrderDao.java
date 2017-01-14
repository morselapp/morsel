package com.morsel.dao;

import com.morsel.models.Order;
import com.sun.jersey.spi.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class OrderDao extends AbstractDAO<Order>{

    public OrderDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
