package com.morsel.service.service;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.policy.RecordExistsAction;
import com.aerospike.client.policy.WritePolicy;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by kunalsingh.k on 01/04/17.
 */
public class UserService {


    private AerospikeClient client;
    @Inject
    public UserService(AerospikeClient c) {
        this.client = c;
    }

    public void createUser() throws AerospikeException, IOException {
        System.out.println("\n********** Create User **********\n");

        String username;
        String password;
        String gender;
        String region;
        String interests;

        // Get username
        System.out.println("Enter username: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        username = br.readLine();
        if (username != null && username.length() > 0) {
            // Get password
            System.out.println("Enter password for " + username + ":");
            password = br.readLine();

            // Get gender
            System.out.println("Select gender (f or m) for " + username + ":");
            gender = br.readLine().substring(0, 1);

            // Get region
            System.out.println("Select region (north, south, east or west) for "
                    + username + ":");
            region = br.readLine().substring(0, 1);

            // Get interests
            System.out.println("Enter comma-separated interests for " + username + ":");
            interests = br.readLine();

            // Write record
            WritePolicy wPolicy = new WritePolicy();
            wPolicy.recordExistsAction = RecordExistsAction.UPDATE;

            Key key = new Key("test", "users", username);
            Bin bin1 = new Bin("username", username);
            Bin bin2 = new Bin("password", password);
            Bin bin3 = new Bin("gender", gender);
            Bin bin4 = new Bin("region", region);
            Bin bin5 = new Bin("lasttweeted", 0);
            Bin bin6 = new Bin("tweetcount", 0);
            Bin bin7 = Bin.asBlob("interests", Arrays.asList(interests.split(",")));

            client.put(wPolicy, key, bin1, bin2, bin3, bin4, bin5, bin6, bin7);

            System.out.println("\nINFO: User record created!");
        }
    }
}