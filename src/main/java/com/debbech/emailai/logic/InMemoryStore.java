package com.debbech.emailai.logic;


import com.debbech.emailai.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class InMemoryStore implements IInMemoryStore{

    private static Map<String, Task> datastore;

    public InMemoryStore(){

    }

    @Override
    public synchronized void addOne(Task task) {
        if(datastore == null) datastore = new ConcurrentHashMap<>();
        datastore.put(task.getWriteRequest().getName(), task);
    }

    @Override
    public synchronized void deleteOne(Task task) {
        if(datastore == null) datastore = new ConcurrentHashMap<>();
        datastore.remove(task.getWriteRequest().getName());
    }

    @Override
    public synchronized List<Task> getAll() {
        if(datastore == null) datastore = new ConcurrentHashMap<>();
        return new ArrayList<>(datastore.values());
    }

    @Override
    public synchronized Task getOne(String name) {
        if(datastore == null) datastore = new ConcurrentHashMap<>();
        return datastore.get(name);
    }
}
