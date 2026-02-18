package com.example.hql.dto;

public class StudentCourseDTO {

    private final String firstName;
    private final String courseTitle;

    public StudentCourseDTO(String firstName,
                            String courseTitle) {
        this.firstName = firstName;
        this.courseTitle = courseTitle;
    }

    @Override
    public String toString() {
        return firstName + " - " + courseTitle;
    }
}

