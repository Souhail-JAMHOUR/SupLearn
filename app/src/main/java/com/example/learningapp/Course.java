package com.example.learningapp;


import java.time.LocalDate;

public class Course {
    private LocalDate deadline;
    private String name;
    private String description;
    private int logo;

    Course(String name, String description, int logo, LocalDate deadline){
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.deadline = deadline;
    }
    Course(int logo){
        this.logo = logo;
    }

    public LocalDate getDeadline() {
        return deadline;
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

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", logo=" + logo +
                ", deadline=" + deadline +
                '}';
    }
}
