package com.vncodelab.entity;

public class ExerciseSelect {

    private String labName;
    private String date;

    public ExerciseSelect() {

    }

    public ExerciseSelect(String labName, String date) {
        this.labName = labName;
        this.date = date;
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
}
