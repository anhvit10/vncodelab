package com.vncodelab.entity;

import javax.persistence.*;

@Entity
public class Lab {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String docID;
    private String name;
    @Column(length=1000000)
    private String html;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Lab() {

    }

    public Lab(String docID, String name) {
        this.docID = docID;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
