package com.debbech.emailai.controller;

import com.debbech.emailai.logic.IInMemoryStore;
import com.debbech.emailai.logic.IQueueProcessor;
import com.debbech.emailai.model.Task;
import com.debbech.emailai.model.WriteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController("/")
public class MainController {

    @Autowired
    private IQueueProcessor queueProcessor;
    @Autowired
    private IInMemoryStore inMemoryStore;

    @PostMapping("/write")
    ResponseEntity<Object> write(@RequestBody WriteRequest writeRequest){

        if(!inMemoryStore.addOne(new Task(writeRequest))) return ResponseEntity.status(HttpStatus.CONFLICT).body(writeRequest);

        queueProcessor.add(writeRequest);

        return ResponseEntity.ok().body(writeRequest);
    }
    @GetMapping("/read")
    ResponseEntity<Object> read(){

        List<Task> taks = inMemoryStore.getAll();
        taks.sort((a,b) -> {return (int) (b.getStartingTime() - a.getStartingTime());});
        return ResponseEntity.ok().body(taks);
    }
}

