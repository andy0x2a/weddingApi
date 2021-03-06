package com.newmansoft.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rsvp")
public class RSVP {


    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = true)

    private String name;

    @Column(nullable = true)

    private String status;

    @Column(nullable = true)
    private String familyName;

    @Column(nullable = true)
    private String wedding;


    @Transient
    private Integer familyId;


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Transient
    private String comment;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyId) {
        this.familyName = familyName;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public String getWedding() {
        return wedding;
    }

    public void setWedding(String wedding) {
        this.wedding = wedding;
    }


}
