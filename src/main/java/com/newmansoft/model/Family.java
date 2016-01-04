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

    @Column(nullable = true)
    private Integer familySize;

    @Column(nullable = true)
    private String wedding;


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

    public Integer getFamilySize() {
        return familySize;
    }

    public void setFamilySize(Integer size) {
        this.familySize= size;
    }
    public String getWedding() {
        return wedding;
    }

    public void setWedding(String wedding) {
        this.wedding = wedding;
    }
}
