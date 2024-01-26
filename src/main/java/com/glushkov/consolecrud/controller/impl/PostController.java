package com.glushkov.consolecrud.controller.impl;

import com.glushkov.consolecrud.model.Post;
import com.glushkov.consolecrud.repository.PostRepository;
import com.glushkov.consolecrud.repository.impl.gson.GsonPostRepositoryImpl;

import java.util.Collection;

public class PostController {
    private final PostRepository postRepository = new GsonPostRepositoryImpl();

    public Post getByID(long id) {
        return postRepository.getByID(id);
    }

    public void add(Post post) {
        postRepository.save(post);
    }

    public Collection<Post> getAll() {
        return postRepository.getAll();
    }

    public void delete(long id) {
        postRepository.delete(id);
    }

    public void edit(Post post) {
        postRepository.edit(post);
    }
}
