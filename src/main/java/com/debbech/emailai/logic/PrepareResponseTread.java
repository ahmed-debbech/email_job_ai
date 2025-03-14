package com.debbech.emailai.logic;

import com.debbech.emailai.model.WriteRequest;
import com.debbech.emailai.model.WriteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class PrepareResponseTread implements Runnable{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private WriteRequest writeRequest;
    private Future<WriteResponse> writeResponseFuture;

    public PrepareResponseTread(WriteRequest wr, Future<WriteResponse> resultToBe) {
        this.writeRequest = wr;
        this.writeResponseFuture = resultToBe;
    }

    @Override
    public void run() {
        while(!this.writeResponseFuture.isDone());

        log.info("done processing request with name: {}", this.writeRequest.getName());
    }
}
