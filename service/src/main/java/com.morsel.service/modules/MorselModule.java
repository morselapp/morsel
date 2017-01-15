package com.morsel.service.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.morsel.dao.UserDao;
import com.morsel.service.config.HelloWorldConfiguration;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

/**
 * Created by kunalsingh.k on 14/01/17.
 */
public class MorselModule extends AbstractModule {
    protected final HibernateBundle<HelloWorldConfiguration> hibernateBundle;

    public MorselModule(HibernateBundle<HelloWorldConfiguration> hibernateBundle) {
        this.hibernateBundle = hibernateBundle;
    }

    @Override
    protected void configure() {
        bind(UserDao.class).in(Singleton.class);
    }

    @Provides
    SessionFactory getSessionFactory(Provider<HelloWorldConfiguration> configuration) {
        return hibernateBundle.getSessionFactory();
    }
}
