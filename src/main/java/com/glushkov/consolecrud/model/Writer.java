package com.glushkov.consolecrud.model;

import java.util.List;

public class Writer extends BaseItem {
    private String firstName;

    private String lastName;
    private List<Post> posts;

    public Writer(Status status) {
        super(status);
    }

    public Writer(Status status, long id) {
        super(status, id);
    }

    public Writer(Status status, String firstName, String lastName, long id) {
        super(status, id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Writer(Status status, String firstName, String lastName) {
        super(status);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Writer(Status status, String firstName, String lastName, List<Post> posts) {
        this(status, firstName, lastName);
        this.posts = posts;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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
