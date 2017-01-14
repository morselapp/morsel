package com.morsel.service.service;
import com.morsel.service.HelloWorldConfiguration;
import com.morsel.service.HelloWorldResource;
import com.morsel.service.TemplateHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by kunalsingh.k on 07/01/17.
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String [] args) throws Exception{
        System.out.println("Hello World "+args.toString());
        new HelloWorldApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {

    }

    @Override
    public void run(HelloWorldConfiguration helloWorldConfiguration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(helloWorldConfiguration.getTemplate(), helloWorldConfiguration.getDefaultName());
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck();
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}
