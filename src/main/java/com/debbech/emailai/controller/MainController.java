package com.debbech.emailai.controller;

import com.debbech.emailai.logic.IQueueProcessor;
import com.debbech.emailai.model.WriteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class MainController {

    @Autowired
    private IQueueProcessor queueProcessor;

    @PostMapping("/write")
    ResponseEntity<Object> write(@RequestBody WriteRequest writeRequest){

        queueProcessor.add(writeRequest);

        return ResponseEntity.ok().body(writeRequest);
    }
}

