package com.newmansoft.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by v-annew on 25-Nov-15.
 */
@Entity
@Table(name = "family")
public class Family {


    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = true)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RSVP> getMembers() {
        return members;
    }

    public void setMembers(List<RSVP> members) {
        this.members = members;
    }

    @OneToMany
    private List<RSVP> members;

}