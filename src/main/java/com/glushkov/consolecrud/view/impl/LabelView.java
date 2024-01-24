package com.glushkov.consolecrud.view.impl;

import com.glushkov.consolecrud.controller.impl.LabelController;
import com.glushkov.consolecrud.controller.impl.PostController;
import com.glushkov.consolecrud.model.Label;
import com.glushkov.consolecrud.model.Status;
import com.glushkov.consolecrud.exceprion.MyException;
import com.glushkov.consolecrud.view.Message;
import com.glushkov.consolecrud.view.View;

import java.util.Scanner;

public class LabelView implements View {

    private final PostController postController = new PostController();
    private final LabelController labelController = new LabelController();
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void delete() {
        System.out.println(Message.ENTER_ID);

        try {
            labelController.delete(sc.nextLong());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void edit() {
        System.out.println(Message.ENTER_ID);
        try {
            long id = sc.nextLong();
            System.out.println("Текущий элемент:\n" + labelController.getByID(id));

            Label label = new Label(Status.ACTIVE);
            addLabel(label);

            labelController.edit(label, id);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

    }

    private void addLabel(Label label) {
        System.out.println(Message.ENTER_NAME);
        label.setName(sc.next());

        System.out.println(postController.getAll());
        System.out.println(Message.ENTER_POST);
        label.setPostID(sc.nextLong());
    }

    @Override
    public void getAll() {
        try {
            System.out.println(labelController.getAll());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add() {
        Label label = new Label(Status.ACTIVE);
        addLabel(label);
        labelController.add(label);
    }

    @Override
    public void getByID() {

        System.out.println(Message.ENTER_ID);
        try {
            System.out.println(labelController.getByID(sc.nextInt()));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

    }
}
