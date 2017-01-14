package com.morsel.service.service;

import com.morsel.models.*;
import com.morsel.service.config.HelloWorldConfiguration;
import com.morsel.service.HelloWorldResource;
import com.morsel.service.TemplateHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.Hibernate;

/**
 * Created by kunalsingh.k on 07/01/17.
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    private HibernateBundle<HelloWorldConfiguration> hibernateBundle ;
    public static void main(String [] args) throws Exception{
        System.out.println("Hello World "+args.toString());
        new HelloWorldApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        hibernateBundle = new HibernateBundle<HelloWorldConfiguration>(User.class,
                MenuItem.class,
                Order.class,
                OrderItem.class) {

            public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration helloWorldConfiguration) {
                return helloWorldConfiguration.getDataSourceFactory();
            }
        };
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(HelloWorldConfiguration helloWorldConfiguration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(helloWorldConfiguration.getTemplate(), helloWorldConfiguration.getDefaultName());
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck();
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}
