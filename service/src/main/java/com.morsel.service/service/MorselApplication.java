package com.morsel.service.service;

import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.morsel.models.*;
import com.morsel.service.config.MorselConfiguration;
import com.morsel.service.MorselResource;
import com.morsel.service.TemplateHealthCheck;
import com.morsel.service.modules.MorselModule;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import flipkart.dsp.santa.dropwizard.logging.RequestContextBundle;

/**
 * Created by kunalsingh.k on 07/01/17.
 */
public class MorselApplication extends Application<MorselConfiguration> {

    private GuiceBundle guiceBundle;
    private final HibernateBundle<MorselConfiguration> hibernateBundle = new HibernateBundle<MorselConfiguration>(User.class,
            MenuItem.class,
            FoodOrder.class,
            OrderItem.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(MorselConfiguration morselConfiguration) {
            return morselConfiguration.getDataSourceFactory();
        }
    };
    public static void main(String [] args) throws Exception{
        System.out.println("Hello World "+args.toString());
        new MorselApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<MorselConfiguration> bootstrap) {

        GuiceBundle.Builder<MorselConfiguration> guiceBundleBuilder = GuiceBundle.newBuilder();
        guiceBundle = guiceBundleBuilder
                .setConfigClass(MorselConfiguration.class)
                .addModule(new MorselModule(hibernateBundle))
                .build(Stage.DEVELOPMENT);

        bootstrap.addBundle(guiceBundle);
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new AssetsBundle("/assets/","/","/index.html"));
        bootstrap.addBundle(new RequestContextBundle("/*"));
    }

    @Override
    public void run(MorselConfiguration morselConfiguration, Environment environment) throws Exception {
        environment.jersey().setUrlPattern("/api/*");
        //environment.jersey().register(MorselResource.class);
        final MorselResource resource = new MorselResource(morselConfiguration.getTemplate(), morselConfiguration.getDefaultName());
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck();
        environment.healthChecks().register("template", healthCheck);
       // environment.jersey().register(UserResource.class);
        environment.jersey().register(resource);
    }
}
