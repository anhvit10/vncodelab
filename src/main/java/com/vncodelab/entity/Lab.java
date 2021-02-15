package com.vncodelab.entity;

import javax.persistence.*;

@Entity
public class Lab {
    @Id
    private String docID;
    private String name;
    private String description;
    @Column(length = 1000000)
    private String html;

    private int cateID;

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Lab() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Lab(String docID, String name) {
        this.docID = docID;
        this.name = name;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
