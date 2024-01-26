package com.glushkov.consolecrud.model;

public class Label extends BaseItem {
    private String name;

    public Label(Status status) {
        super(status);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Label{" + super.toString() +
                "name='" + name + '\'' +
                "}\n";
    }
}
