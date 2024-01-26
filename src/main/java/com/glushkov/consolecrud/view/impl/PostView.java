package com.glushkov.consolecrud.view.impl;

import com.glushkov.consolecrud.controller.impl.LabelController;
import com.glushkov.consolecrud.controller.impl.PostController;
import com.glushkov.consolecrud.model.Label;
import com.glushkov.consolecrud.model.Post;
import com.glushkov.consolecrud.model.Status;
import com.glushkov.consolecrud.exceprion.MyException;
import com.glushkov.consolecrud.view.Message;
import com.glushkov.consolecrud.view.View;

import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PostView implements View {

    private final PostController POST_CONTROLLER = new PostController();
    private final LabelController LABEL_CONTROLLER = new LabelController();
    private final Scanner SC = new Scanner(System.in);

    @Override
    public void delete() {
        System.out.println(Message.ENTER_ID);

        try {
            POST_CONTROLLER.delete(SC.nextLong());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void edit() {
        System.out.println(Message.ENTER_ID);
        try {
            long id = SC.nextLong();
            System.out.println("Текущий элемент:\n" + POST_CONTROLLER.getByID(id));

            Post post = new Post(Status.ACTIVE, id);
            addPost(post);

            POST_CONTROLLER.edit(post);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addPost(Post post) {
        System.out.println(Message.ENTER_TITLE);
        post.setTitle(SC.next());

        System.out.println(Message.ENTER_CONTENT);
        post.setContent(SC.next());

        Collection<Label> labels = LABEL_CONTROLLER.getAll();
        System.out.println(labels);
        System.out.println(Message.ENTER_LABEL);
        post.setLabels(Stream.of(SC.next().split(","))
                .mapToLong(Long::parseLong)
                .mapToObj(LABEL_CONTROLLER::getByID)
                .collect(Collectors.toList()));
    }

    @Override
    public void getAll() {
        try {
            System.out.println(POST_CONTROLLER.getAll());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add() {
        Post post = new Post(Status.ACTIVE);
        addPost(post);
        POST_CONTROLLER.add(post);
    }

    @Override
    public void getByID() {

        System.out.println(Message.ENTER_ID);
        try {
            System.out.println(POST_CONTROLLER.getByID(SC.nextInt()));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
