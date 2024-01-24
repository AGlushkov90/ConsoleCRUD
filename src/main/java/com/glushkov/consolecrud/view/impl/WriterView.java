package com.glushkov.consolecrud.view.impl;

import com.glushkov.consolecrud.controller.impl.WriterController;
import com.glushkov.consolecrud.model.Status;
import com.glushkov.consolecrud.model.Writer;
import com.glushkov.consolecrud.exceprion.MyException;
import com.glushkov.consolecrud.view.Message;
import com.glushkov.consolecrud.view.View;

import java.util.ArrayList;
import java.util.Scanner;

public class WriterView implements View {

    private final WriterController writerController = new WriterController();
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void delete() {
        System.out.println(Message.ENTER_ID);

        try {
            writerController.delete(sc.nextLong());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void edit() {
        System.out.println(Message.ENTER_ID);
        try {
            long id = sc.nextLong();
            System.out.println("Текущий элемент:\n" + writerController.getByID(id));

            System.out.println(Message.ENTER_FIRSTNAME);
            String firstName = sc.next();

            System.out.println(Message.ENTER_LASTNAME);
            String lastName = sc.next();
            writerController.edit(new Writer(Status.ACTIVE, firstName, lastName), id);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void getAll() {
        try {
            System.out.println(writerController.getAll());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add() {
        System.out.println(Message.ENTER_FIRSTNAME);
        String firstName = sc.next();

        System.out.println(Message.ENTER_LASTNAME);
        String lastName = sc.next();
        writerController.add(new Writer(Status.ACTIVE, firstName, lastName, new ArrayList<>()));
    }

    @Override
    public void getByID() {

        System.out.println(Message.ENTER_ID);
        try {
            System.out.println(writerController.getByID(sc.nextInt()));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

    }
}
