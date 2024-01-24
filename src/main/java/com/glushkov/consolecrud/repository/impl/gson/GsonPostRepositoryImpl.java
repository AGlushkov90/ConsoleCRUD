package com.glushkov.consolecrud.repository.impl.gson;

import com.glushkov.consolecrud.model.*;
import com.glushkov.consolecrud.repository.PostRepository;
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

public class GsonPostRepositoryImpl implements PostRepository {
    private final static String fileName = "Post.json";
    GsonLabelRepositoryImpl labelRepository = new GsonLabelRepositoryImpl();

    @Override
    public Post getByID(Long id){
         return getAll().stream().filter(a -> a.getId() == id).findFirst().orElseThrow(() -> {
            throw new MyException(Message.NOT_FIND_ID.toString() + id);
        });
    }

    @Override
    public Collection<Post> getAll() {
        Type targetClassType = new TypeToken<ArrayList<Post>>() {}.getType();
        String text = FileUtil.read(fileName);
        if(text.equals(""))
        {
            return new ArrayList<>();
        }
        Collection<Post> collectionPost = new Gson().fromJson(text, targetClassType);
        Collection<Label> collectionLabel = labelRepository.getAll();
            if(!collectionLabel.isEmpty()){
                for(Post post : collectionPost){
                    post.getLabels().clear();
                    for(Label label : collectionLabel){
                        if(label.getPostID() == post.getId()){
                            post.getLabels().add(label);
                        }
                    }
                }
            }
        return collectionPost.stream().sorted(Comparator.comparing(BaseItem::getId)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        boolean deleted = false;
        Collection<Post> collection = getAll();
        for(Post post : collection){
            if(post.getId() == id){
                post.setStatus(Status.DELETED);
                deleted = true;
            }
        }
        if (!deleted){
            throw new MyException(Message.NOT_FIND_ID + String.valueOf(id));
        }
        FileUtil.add(fileName, new Gson().toJson(collection));
    }

    @Override
    public void edit(Post item, Long id) throws MyException {
        boolean deleted = false;
        Collection<Post> collection = getAll();
        for(Post post : collection){
            if(post.getId() == id){
                post.setTitle(item.getTitle());
                post.setContent(item.getContent());
                post.setWriterID(item.getWriterID());
                deleted = true;
            }
        }
        if (!deleted){
            throw new MyException(Message.NOT_FIND_ID + String.valueOf(id));
        }
        FileUtil.add(fileName, new Gson().toJson(collection));
    }
}
