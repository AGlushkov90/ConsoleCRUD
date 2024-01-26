package com.glushkov.consolecrud.controller.impl;

import com.glushkov.consolecrud.model.Writer;
import com.glushkov.consolecrud.repository.WriterRepository;
import com.glushkov.consolecrud.repository.impl.gson.GsonWriterRepositoryImpl;

import java.util.Collection;

public class WriterController {
    private final WriterRepository writerRepository = new GsonWriterRepositoryImpl();

    public Writer getByID(long id) {
        return writerRepository.getByID(id);
    }

    public void add(Writer writer) {
        writerRepository.save(writer);
    }

    public Collection<Writer> getAll() {
        return writerRepository.getAll();
    }

    public void delete(long id) {
        writerRepository.delete(id);
    }

    public void edit(Writer writer) {
        writerRepository.edit(writer);
    }
}
