package com.debbech.emailai.logic;

import com.debbech.emailai.model.Task;

import java.util.List;

public interface IInMemoryStore {

    boolean addOne(Task task);
    void deleteOne(Task task);
    List<Task> getAll();

    Task getOne(String name);
}
