package com.glushkov.consolecrud.model;

import java.util.List;

public class Writer extends BaseItem {
    private String firstName, lastName;
    private List<Post> posts;

    public Writer(Status status) {
        super(status);
    }

    public Writer(Status status, String firstName, String lastName) {
        this(status);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Writer(Status status, String firstName, String lastName, List<Post> posts) {
        this(status, firstName, lastName);
        this.posts = posts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return "Writer{" + super.toString() +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", posts=" + posts +
                "}\n";
    }
}
