package com.glushkov.consolecrud.controller.impl;

import com.glushkov.consolecrud.model.Label;
import com.glushkov.consolecrud.repository.LabelRepository;
import com.glushkov.consolecrud.repository.impl.gson.GsonLabelRepositoryImpl;

import java.util.Collection;

public class LabelController {
    private final LabelRepository labelRepository = new GsonLabelRepositoryImpl();

    public Label getByID(long id) {
        return labelRepository.getByID(id);
    }

    public void add(Label label) {
        labelRepository.save(label);
    }

    public Collection<Label> getAll() {
        return labelRepository.getAll();
    }

    public void delete(long id) {
        labelRepository.delete(id);
    }

    public void edit(Label label) {
        labelRepository.edit(label);
    }
}
