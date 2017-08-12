package com.morsel.service.service;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by kunalsingh.k on 01/04/17.
 */
public class Aerospike {

    private static UserService userService;

    @Inject
    public Aerospike(UserService userService){
        this.userService = userService;
    }

    public static void main(String[] args) {
        try {
            userService.createUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
