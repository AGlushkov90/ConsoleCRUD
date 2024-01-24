package com.glushkov.consolecrud.repository;

import com.glushkov.consolecrud.model.BaseItem;
import com.glushkov.consolecrud.exceprion.MyException;
import com.glushkov.consolecrud.util.FileUtil;
import com.google.gson.Gson;

import java.util.Collection;

public interface GenericRepository<T extends BaseItem, ID extends Long> {
    T getByID(ID id) throws MyException;

    Collection<T> getAll();

    void delete(ID id) throws MyException;

    void edit(T item, ID id) throws MyException;

    default void save(T item) {
        Collection<T> collection = getAll();
        long idMax;
        if (collection.isEmpty()) {
            idMax = 1;
        } else {
            idMax = collection.stream().mapToLong(BaseItem::getId).max().orElse(0) + 1;
        }
        item.setId(idMax);
        collection.add(item);
        FileUtil.add(FileUtil.getFileName(item), new Gson().toJson(collection));
    }
}
