package com.glushkov.consolecrud.view.impl;

import com.glushkov.consolecrud.controller.impl.PostController;
import com.glushkov.consolecrud.controller.impl.WriterController;
import com.glushkov.consolecrud.model.Post;
import com.glushkov.consolecrud.model.Status;
import com.glushkov.consolecrud.model.Writer;
import com.glushkov.consolecrud.exceprion.MyException;
import com.glushkov.consolecrud.view.Message;
import com.glushkov.consolecrud.view.View;

import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WriterView implements View {

    private final WriterController WRITER_CONTROLLER = new WriterController();
    private final PostController POST_CONTROLLER = new PostController();
    private final Scanner SC = new Scanner(System.in);

    @Override
    public void delete() {
        System.out.println(Message.ENTER_ID);

        try {
            WRITER_CONTROLLER.delete(SC.nextLong());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void edit() {
        System.out.println(Message.ENTER_ID);
        try {
            long id = SC.nextLong();
            System.out.println("Текущий элемент:\n" + WRITER_CONTROLLER.getByID(id));
            Writer writer = new Writer(Status.ACTIVE, id);
            addWriter(writer);
            WRITER_CONTROLLER.edit(writer);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getAll() {
        try {
            System.out.println(WRITER_CONTROLLER.getAll());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add() {
        Writer writer = new Writer(Status.ACTIVE);
        addWriter(writer);
        WRITER_CONTROLLER.add(writer);
    }

    @Override
    public void getByID() {

        System.out.println(Message.ENTER_ID);
        try {
            System.out.println(WRITER_CONTROLLER.getByID(SC.nextInt()));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

    }

    private void addWriter(Writer writer) {
        System.out.println(Message.ENTER_FIRSTNAME);
        writer.setFirstName(SC.next());

        System.out.println(Message.ENTER_LASTNAME);
        writer.setLastName(SC.next());

        Collection<Post> posts = POST_CONTROLLER.getAll();
        System.out.println(posts);
        System.out.println(Message.ENTER_POSTS);
        writer.setPosts(Stream.of(SC.next().split(","))
                .mapToLong(Long::parseLong)
                .mapToObj(POST_CONTROLLER::getByID)
                .collect(Collectors.toList()));
    }
}
