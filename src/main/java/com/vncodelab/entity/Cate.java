package com.vncodelab.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cateID;
    private String name;
    private String description;
    private int type = 0;

    public Cate() {

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Cate(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Cate(String name, String description, int type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
