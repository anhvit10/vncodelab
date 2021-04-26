package com.vncodelab.entity;

import javax.persistence.*;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exerciseID")
    private Integer exerciseID;

    @Column(name = "lab_name")
    private String labName;

    @Column(name = "date")
    private String date;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    public Exercise() {

    }

    public Exercise(String labName, String date, String description, String url) {
        this.labName = labName;
        this.date = date;
        this.description = description;
        this.url = url;
    }

    public Integer getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(Integer exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
