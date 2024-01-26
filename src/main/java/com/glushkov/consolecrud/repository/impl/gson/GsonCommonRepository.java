package com.glushkov.consolecrud.repository.impl.gson;

import com.glushkov.consolecrud.model.BaseItem;

import java.util.Collection;

public class GsonCommonRepository {

    public static <T extends BaseItem> Long generateId(Collection<T> collection) {
        return collection.stream().mapToLong(BaseItem::getId).max().orElse(0) + 1;
    }
}
