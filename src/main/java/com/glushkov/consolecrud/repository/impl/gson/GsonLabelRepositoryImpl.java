package com.glushkov.consolecrud.repository.impl.gson;

import com.glushkov.consolecrud.model.BaseItem;
import com.glushkov.consolecrud.model.Label;
import com.glushkov.consolecrud.model.Status;
import com.glushkov.consolecrud.repository.LabelRepository;
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

public class GsonLabelRepositoryImpl implements LabelRepository {
    private final static String fileName = "Label.json";

    @Override
    public Label getByID(Long id) {
        return getAll().stream().filter(a -> a.getId() == id).findFirst().orElseThrow(() -> {
            throw new MyException(Message.NOT_FIND_ID.toString() + id);
        });
    }

    @Override
    public Collection<Label> getAll() {
        Type targetClassType = new TypeToken<ArrayList<Label>>() {}.getType();
        String text = FileUtil.read(fileName);
        if(text.equals(""))
        {
            return new ArrayList<>();
        }
        Collection<Label> collection = new Gson().fromJson(text, targetClassType);
        return collection.stream().sorted(Comparator.comparing(BaseItem::getId)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        boolean deleted = false;
        Collection<Label> collection = getAll();
        for (Label label : collection) {
            if (label.getId() == id) {
                label.setStatus(Status.DELETED);
                deleted = true;
            }
        }
        if (!deleted) {
            throw new MyException(Message.NOT_FIND_ID + String.valueOf(id));
        }
        FileUtil.add(fileName, new Gson().toJson(collection));
    }

    @Override
    public void edit(Label item, Long id) throws MyException {
        boolean deleted = false;
        Collection<Label> collection = getAll();
        for (Label label : collection) {
            if (label.getId() == id) {
                label.setName(item.getName());
                label.setPostID(item.getPostID());
                deleted = true;
            }
        }
        if (!deleted) {
            throw new MyException(Message.NOT_FIND_ID + String.valueOf(id));
        }
        FileUtil.add(fileName, new Gson().toJson(collection));
    }


}
