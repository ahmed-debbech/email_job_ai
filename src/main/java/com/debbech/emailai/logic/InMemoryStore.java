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
        this.datastore = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized void addOne(Task task) {
        datastore.put(task.getWriteRequest().getName(), task);
    }

    @Override
    public synchronized void deleteOne(Task task) {
        datastore.remove(task.getWriteRequest().getName());
    }

    @Override
    public synchronized List<Task> getAll() {
        return new ArrayList<>(this.datastore.values());
    }
}
