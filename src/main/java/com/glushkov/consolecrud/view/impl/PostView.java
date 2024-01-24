package com.glushkov.consolecrud.view.impl;

import com.glushkov.consolecrud.controller.impl.PostController;
import com.glushkov.consolecrud.controller.impl.WriterController;
import com.glushkov.consolecrud.model.Post;
import com.glushkov.consolecrud.model.Status;
import com.glushkov.consolecrud.exceprion.MyException;
import com.glushkov.consolecrud.view.Message;
import com.glushkov.consolecrud.view.View;

import java.util.ArrayList;
import java.util.Scanner;

public class PostView implements View {

    private final PostController postController = new PostController();
    private final WriterController writerController = new WriterController();
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void delete() {
        System.out.println(Message.ENTER_ID);

        try {
            postController.delete(sc.nextLong());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void edit() {
        System.out.println(Message.ENTER_ID);
        try {
            long id = sc.nextLong();
            System.out.println("Текущий элемент:\n" + postController.getByID(id));

            Post post = new Post(Status.ACTIVE);
            addPost(post);

            postController.edit(post, id);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addPost(Post post) {
        System.out.println(Message.ENTER_TITLE);
        post.setTitle(sc.next());

        System.out.println(Message.ENTER_CONTENT);
        post.setContent(sc.next());

        System.out.println(writerController.getAll());
        System.out.println(Message.ENTER_WRITER);
        post.setWriterID(sc.nextLong());
    }

    @Override
    public void getAll() {
        try {
            System.out.println(postController.getAll());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add() {
        Post post = new Post(Status.ACTIVE);
        addPost(post);
        post.setLabels(new ArrayList<>());
        postController.add(post);
    }

    @Override
    public void getByID() {

        System.out.println(Message.ENTER_ID);
        try {
            System.out.println(postController.getByID(sc.nextInt()));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

    }
}
