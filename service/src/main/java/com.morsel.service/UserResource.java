package com.morsel.service;

import com.codahale.metrics.annotation.Timed;
import com.morsel.dao.UserDao;
import com.morsel.models.User;
import com.sun.jersey.api.core.InjectParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

/**
 * Created by ajeet on 14/1/17.
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)

public class UserResource {

    private UserDao userDao;
    public UserResource(@InjectParam UserDao userDao){
        this.userDao = userDao;
    }

    @GET
    @Timed
    @Path("/{userId}")
    public User getUser(@PathParam("userId") int userId) {
        return (User)userDao.getById(userId);
    }

    @POST
    public Serializable addUser(User user) {
        Serializable userId = userDao.add(user);
        return userId;
    }
}
