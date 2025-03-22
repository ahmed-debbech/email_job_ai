package com.debbech.emailai.controller;

import com.debbech.emailai.Config;
import com.debbech.emailai.logic.IInMemoryStore;
import com.debbech.emailai.logic.IQueueProcessor;
import com.debbech.emailai.logic.TemplateEngine;
import com.debbech.emailai.model.ConfigModel;
import com.debbech.emailai.model.Task;
import com.debbech.emailai.model.WriteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController("/")
public class MainController {

    @Autowired
    private IQueueProcessor queueProcessor;
    @Autowired
    private IInMemoryStore inMemoryStore;
    @Autowired
    private Config configuration;

    @PostMapping("/write")
    ResponseEntity<Object> write(@RequestBody WriteRequest writeRequest){

        if(!configuration.isConfigSet()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("config is not set yet");

        if(!inMemoryStore.addOne(new Task(writeRequest))) return ResponseEntity.status(HttpStatus.CONFLICT).body(writeRequest);

        queueProcessor.add(writeRequest);

        return ResponseEntity.ok().body(writeRequest);
    }
    @GetMapping("/read")
    ResponseEntity<Object> read(){

        if(!configuration.isConfigSet()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("config is not set yet");

        List<Task> taks = inMemoryStore.getAll();
        taks.sort((a,b) -> {return (int) (b.getStartingTime() - a.getStartingTime());});
        return ResponseEntity.ok().body(taks);
    }

    @PutMapping("/setconfig")
    ResponseEntity<Object> setConfig(@RequestBody ConfigModel configModel){

        boolean ipSet = false;
        boolean modelSet = false;

        if(configModel.getOllamaIp() != null && !configModel.getOllamaIp().isEmpty()) {
            this.configuration.setOllamaIp(configModel.getOllamaIp());
            ipSet=true;
        }
        if (configModel.getOllamaModel() != null && !configModel.getOllamaModel().isEmpty()) {
            this.configuration.setOllamaModel(configModel.getOllamaModel());
            modelSet=true;
        }
        if(ipSet && modelSet){
            this.configuration.configIsSet(true);
        }
        if(configModel.getTemp1() != null && !configModel.getTemp1().isEmpty()){
             new TemplateEngine().storeTemplate(configModel.getTemp1(), 1);
        }
        return ResponseEntity.ok().body("SUCCESS");

    }
}

