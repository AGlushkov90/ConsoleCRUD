package com.glushkov.consolecrud.model;

import java.util.List;

public class Post extends BaseItem {
    private String title;
    private String content;
    private List<Label> labels;

    public Post(Status status) {
        super(status);
    }

    public Post(Status status, long id) {
        super(status, id);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Post{" + super.toString() +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", labels=" + labels +
                "}\n";
    }
}
