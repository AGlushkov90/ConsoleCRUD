package com.glushkov.consolecrud.repository.impl.gson;

import com.glushkov.consolecrud.model.BaseItem;
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

    private final static String FILE_NAME = "Writer.json";

    @Override
    public Writer getByID(Long id) {
        return getAll().stream().filter(a -> a.getId() == id).findFirst().orElseThrow(() -> {
            throw new MyException(Message.NOT_FIND_ID + String.valueOf(id));
        });
    }

    @Override
    public Collection<Writer> getAll() {
        return getAllWritersInternal();
    }

    @Override
    public boolean delete(Long id) {
        Collection<Writer> collection = getAllWritersInternal().stream().peek(label -> {
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
    public Writer edit(Writer writerToUpdate) throws MyException {
        Collection<Writer> collection = getAllWritersInternal()
                .stream().map(existingLabel -> {
                    if (existingLabel.getId() == writerToUpdate.getId()) {
                        return writerToUpdate;
                    }
                    return existingLabel;
                }).toList();

        FileUtil.add(FILE_NAME, new Gson().toJson(collection));
        return writerToUpdate;
    }

    private Collection<Writer> getAllWritersInternal() {
        Type targetClassType = new TypeToken<ArrayList<Writer>>() {
        }.getType();
        String text = FileUtil.read(FILE_NAME);
        if (text.equals("")) {
            return new ArrayList<>();
        }
        Collection<Writer> collection = new Gson().fromJson(text, targetClassType);
        return collection.stream().sorted(Comparator.comparing(BaseItem::getId)).collect(Collectors.toList());
    }

    @Override
    public Writer save(Writer itemToSave) {
        Collection<Writer> collection = getAllWritersInternal();
        Long newId = GsonCommonRepository.generateId(collection);
        itemToSave.setId(newId);
        collection.add(itemToSave);
        FileUtil.add(FileUtil.getFileName(itemToSave), new Gson().toJson(collection));
        return itemToSave;
    }
}

