package com.example.learningapp;


import java.time.LocalDate;

public class Course {
    private LocalDate deadline, endingDate;
    private String name;
    private String description;
    private int logo;

    Course(String name, String description, int logo, LocalDate deadline, LocalDate endingDate){
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.deadline = deadline;
        this.endingDate = endingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
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
