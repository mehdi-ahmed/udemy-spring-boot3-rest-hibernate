package com.mytutorials.springboot.model;

import validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName = "";

    @Max(value = 10, message = "Must be less than or Equal to 10")
    @Min(value = 0, message = "Must be greater than or Equal to 0")
    @NotNull(message = "is required")
    private Integer freePasses = 0;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "Only 5 chars/digits")
    private String postalCode;

    @CourseCode
    private String courseCode;

    public Customer(String firstName, @NotNull(message = "is required") String lastName, @NotNull(message = "is required")
    Integer freePasses, String postalCode, String courseCode) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.freePasses = freePasses;
        this.postalCode = postalCode;
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Customer(String firstName, @NotNull(message = "is required") String lastName, int freePasses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.freePasses = freePasses;
    }

    public Customer(String firstName, @NotNull(message = "is required") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull String lastName) {
        this.lastName = lastName;
    }

    public @NotNull Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(@NotNull Integer freePasses) {
        this.freePasses = freePasses;
    }
}
