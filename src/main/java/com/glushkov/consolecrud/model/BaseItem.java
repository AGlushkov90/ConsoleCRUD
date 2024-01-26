package com.glushkov.consolecrud.model;

public abstract class BaseItem {
    private long id;
    private Status status;

    public BaseItem(Status status) {
        this.status = status;
    }

    public BaseItem(Status status, long id) {
        this(status);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", status=" + status + ", ";
    }
}
