package com.morsel.models;

/**
 * Created by ajeet on 14/1/17.
 */
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
public class FoodOrder implements Serializable {

    @Id
    private int id;
    private int userid;

    public FoodOrder(int id, int userid) {
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
