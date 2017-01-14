package com.morsel.models;

/**
 * Created by ajeet on 14/1/17.
 */
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Order {

    @Id
    private int id;
    private int userid;

    public Order(int id, int userid) {
        this.id = id;
        this.userid = userid;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
