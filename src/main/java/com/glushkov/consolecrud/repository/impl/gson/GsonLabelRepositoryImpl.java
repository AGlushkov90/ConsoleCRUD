package com.glushkov.consolecrud.repository.impl.gson;

import com.glushkov.consolecrud.model.BaseItem;
import com.glushkov.consolecrud.model.Label;
import com.glushkov.consolecrud.model.Status;
import com.glushkov.consolecrud.repository.LabelRepository;
import com.glushkov.consolecrud.util.FileUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class GsonLabelRepositoryImpl implements LabelRepository {
    private final static String FILE_NAME = "Label.json";

    @Override
    public Label getByID(Long id) {
        return getAllLabelsInternal().stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    private Collection<Label> getAllLabelsInternal() {
        Type targetClassType = new TypeToken<ArrayList<Label>>() {
        }.getType();
        String text = FileUtil.read(FILE_NAME);
        if (text.equals("")) {
            return new ArrayList<>();
        }
        Collection<Label> collection = new Gson().fromJson(text, targetClassType);
        return collection.stream().sorted(Comparator.comparing(BaseItem::getId)).collect(Collectors.toList());
    }


    @Override
    public Collection<Label> getAll() {
        return getAllLabelsInternal();
    }

    @Override
    public boolean delete(Long id) {
        Collection<Label> collection = getAllLabelsInternal().stream().peek(label -> {
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
    public Label save(Label itemToSave) {
        Collection<Label> collection = getAllLabelsInternal();
        Long newId = GsonCommonRepository.generateId(collection);
        itemToSave.setId(newId);
        collection.add(itemToSave);
        FileUtil.add(FileUtil.getFileName(itemToSave), new Gson().toJson(collection));
        return itemToSave;
    }

    @Override
    public Label edit(Label labelToUpdate) {
        Collection<Label> collection = getAllLabelsInternal()
                .stream().map(existingLabel -> {
                    if (existingLabel.getId() == labelToUpdate.getId()) {
                        return labelToUpdate;
                    }
                    return existingLabel;
                }).toList();
        FileUtil.add(FILE_NAME, new Gson().toJson(collection));
        return labelToUpdate;
    }
}
