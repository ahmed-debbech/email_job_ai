package com.debbech.emailai.logic;

import com.debbech.emailai.model.WriteRequest;
import com.debbech.emailai.model.WriteResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;

@Service
public class QueueProcessor implements IQueueProcessor {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private Queue<WriteRequest> waitQueue;

    private ExecutorService waitQueueProcessor;
    private ExecutorService postProcessor;


    public QueueProcessor(){
        this.waitQueueProcessor = Executors.newSingleThreadExecutor();
        this.postProcessor = Executors.newFixedThreadPool(3);
        new Thread(() -> {
            while(true){
                this.process();
            }
        }).start();
    }

    @Override
    public void add(WriteRequest writeRequest) {

        if(this.waitQueue == null) this.waitQueue = new LinkedList<>();

        waitQueue.add(writeRequest);
        log.info("new request added with name: {}", writeRequest.getName());
    }

    @Override
    public void process() {
        if(this.waitQueue == null) this.waitQueue = new LinkedList<>();

        WriteRequest wr = this.waitQueue.poll();
        if(wr == null) return;

        log.info("a request has been polled to be processed with name: {}", wr.getName());
        Future<WriteResponse> resultToBe = this.waitQueueProcessor.submit(new AiCallThread(wr));
        this.postProcessor.execute(new PrepareResponseTread(wr, resultToBe));
    }
}
