package com.glushkov.consolecrud.view.impl;

import com.glushkov.consolecrud.controller.impl.LabelController;
import com.glushkov.consolecrud.model.Label;
import com.glushkov.consolecrud.model.Status;
import com.glushkov.consolecrud.exceprion.MyException;
import com.glushkov.consolecrud.view.Message;
import com.glushkov.consolecrud.view.View;

import java.util.Scanner;

public class LabelView implements View {

    private final LabelController LABEL_CONTROLLER = new LabelController();
    private final Scanner SC = new Scanner(System.in);

    @Override
    public void delete() {
        System.out.println(Message.ENTER_ID);
        try {
            LABEL_CONTROLLER.delete(SC.nextLong());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void edit() {
        System.out.println(Message.ENTER_ID);
        try {
            long id = SC.nextLong();
            System.out.println("Текущий элемент:\n" + LABEL_CONTROLLER.getByID(id));
            Label label = new Label(Status.ACTIVE);
            label.setId(id);
            addLabel(label);

            LABEL_CONTROLLER.edit(label);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addLabel(Label label) {
        System.out.println(Message.ENTER_NAME);
        label.setName(SC.next());
    }

    @Override
    public void getAll() {
        try {
            System.out.println(LABEL_CONTROLLER.getAll());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add() {
        Label label = new Label(Status.ACTIVE);
        addLabel(label);
        LABEL_CONTROLLER.add(label);
    }

    @Override
    public void getByID() {
        System.out.println(Message.ENTER_ID);
        try {
            System.out.println(LABEL_CONTROLLER.getByID(SC.nextInt()));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
