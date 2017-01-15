package com.morsel.dao;

import com.morsel.models.Address;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by ajeet on 14/1/17.
 */
public class AddressDao extends AbstractDAO<Address> {
    public AddressDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
