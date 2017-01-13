package com.example; /**
 * Created by kunalsingh.k on 07/01/17.
 */
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)

public class HelloWorldResource {
    private String template;
    private String defaultName;

    public HelloWorldResource(String template, String defaultName){
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
