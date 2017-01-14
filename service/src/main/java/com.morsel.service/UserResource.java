package com.morsel.service;

import com.codahale.metrics.annotation.Timed;
import com.morsel.dao.UserDao;
import com.morsel.models.User;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

/**
 * Created by ajeet on 14/1/17.
 */
public class UserResource {

    private UserDao userDao;
    public UserResource(){
    }

    @GET
    @Timed
    public User getUser(@QueryParam("userId") int userId) {

    }
}
