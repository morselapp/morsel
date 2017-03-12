package com.morsel.service;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)

public class MorselResource {
    private String template;
    private String defaultName;

    public MorselResource(String template, String defaultName){
        this.defaultName = defaultName;
        this.template = template;
    }

    @GET
    @Timed
    public String sayHello(){
        return "hello "+defaultName;
    }

    @GET
    @Path("/hi")
    public String SayHi(){
        return "hi"+defaultName;
    }
}
