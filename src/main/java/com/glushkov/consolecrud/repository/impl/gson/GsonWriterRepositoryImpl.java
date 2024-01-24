package com.glushkov.consolecrud.repository.impl.gson;

import com.glushkov.consolecrud.model.BaseItem;
import com.glushkov.consolecrud.model.Post;
import com.glushkov.consolecrud.model.Status;
import com.glushkov.consolecrud.model.Writer;
import com.glushkov.consolecrud.repository.WriterRepository;
import com.glushkov.consolecrud.util.FileUtil;
import com.glushkov.consolecrud.exceprion.MyException;
import com.glushkov.consolecrud.view.Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class GsonWriterRepositoryImpl implements WriterRepository {

    private final static String fileName = "Writer.json";
    GsonPostRepositoryImpl postRepository = new GsonPostRepositoryImpl();

    @Override
    public Writer getByID(Long id) {
        return getAll().stream().filter(a -> a.getId() == id).findFirst().orElseThrow(() -> {
            throw new MyException(Message.NOT_FIND_ID + String.valueOf(id));
        });
    }

    @Override
    public Collection<Writer> getAll() {
        Type targetClassType = new TypeToken<ArrayList<Writer>>() {}.getType();
        String text = FileUtil.read(fileName);
        if (text.equals("")) {
            return new ArrayList<>();
        }
        Collection<Writer> collectionWriter = new Gson().fromJson(text, targetClassType);
        Collection<Post> collectionPost = postRepository.getAll();
        if (!collectionPost.isEmpty()) {
            for (Writer writer : collectionWriter) {
                writer.getPosts().clear();
                for (Post post : collectionPost) {
                    if (post.getWriterID() == writer.getId()) {
                        writer.getPosts().add(post);
                    }
                }
            }
        }
        return collectionWriter.stream().sorted(Comparator.comparing(BaseItem::getId)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        boolean deleted = false;
        Collection<Writer> collection = getAll();
        for (Writer writer : collection) {
            if (writer.getId() == id) {
                writer.setStatus(Status.DELETED);
                deleted = true;
            }
        }
        if (!deleted) {
            throw new MyException(Message.NOT_FIND_ID + String.valueOf(id));
        }
        FileUtil.add(fileName, new Gson().toJson(collection));
    }

    @Override
    public void edit(Writer item, Long id) throws MyException {
        boolean deleted = false;
        Collection<Writer> collection = getAll();
        for (Writer writer : collection) {
            if (writer.getId() == id) {
                writer.setFirstName(item.getFirstName());
                writer.setLastName(item.getLastName());
                deleted = true;
            }
        }
        if (!deleted) {
            throw new MyException(Message.NOT_FIND_ID + String.valueOf(id));
        }
        FileUtil.add(fileName, new Gson().toJson(collection));
    }


}

