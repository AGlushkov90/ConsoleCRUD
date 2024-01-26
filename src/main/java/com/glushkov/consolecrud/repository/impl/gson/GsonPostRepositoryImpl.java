package com.glushkov.consolecrud.repository.impl.gson;

import com.glushkov.consolecrud.model.*;
import com.glushkov.consolecrud.repository.PostRepository;
import com.glushkov.consolecrud.util.FileUtil;
import com.glushkov.consolecrud.exceprion.MyException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class GsonPostRepositoryImpl implements PostRepository {
    private final static String FILE_NAME = "Post.json";

    @Override
    public Post getByID(Long id) {
        return getAllPostsInternal().stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Collection<Post> getAll() {
        return getAllPostsInternal();
    }

    @Override
    public boolean delete(Long id) {
        Collection<Post> collection = getAllPostsInternal().stream().peek(label -> {
            if (label.getId() == id) {
                label.setStatus(Status.DELETED);
            }
        }).toList();
        boolean idExists = collection.stream().anyMatch(label -> label.getId() == id);
        if (idExists) {
            FileUtil.add(FILE_NAME, new Gson().toJson(collection));
        }
        return idExists;
    }

    @Override
    public Post edit(Post postToUpdate) throws MyException {
        Collection<Post> collection = getAllPostsInternal()
                .stream().map(existingLabel -> {
                    if (existingLabel.getId() == postToUpdate.getId()) {
                        return postToUpdate;
                    }
                    return existingLabel;
                }).toList();

        FileUtil.add(FILE_NAME, new Gson().toJson(collection));
        return postToUpdate;
    }

    private Collection<Post> getAllPostsInternal() {
        Type targetClassType = new TypeToken<ArrayList<Post>>() {
        }.getType();
        String text = FileUtil.read(FILE_NAME);
        if (text.equals("")) {
            return new ArrayList<>();
        }
        Collection<Post> collection = new Gson().fromJson(text, targetClassType);
        return collection.stream().sorted(Comparator.comparing(BaseItem::getId)).collect(Collectors.toList());
    }

    @Override
    public Post save(Post itemToSave) {
        Collection<Post> collection = getAllPostsInternal();
        Long newId = GsonCommonRepository.generateId(collection);
        itemToSave.setId(newId);
        collection.add(itemToSave);
        FileUtil.add(FileUtil.getFileName(itemToSave), new Gson().toJson(collection));
        return itemToSave;
    }
}
