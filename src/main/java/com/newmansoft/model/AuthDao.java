package com.newmansoft.model;

import javax.persistence.*;

/**
 * Created by v-annew on 14-Dec-15.
 */
@Entity
@Table(name = "authorization")
public class AuthDao {


    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = true)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
