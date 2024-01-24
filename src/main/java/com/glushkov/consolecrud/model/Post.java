package com.glushkov.consolecrud.model;

import java.util.List;

public class Post extends BaseItem {
    private String title, content;
    private List<Label> labels;
    private Long writerID;

    public void setWriterID(Long writerID) {
        this.writerID = writerID;
    }

    public Long getWriterID() {
        return writerID;
    }

    public Post(Status status) {
        super(status);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Post{" + super.toString() +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer id='" + writerID + '\'' +
                ", labels=" + labels +
                "}\n";
    }
}
