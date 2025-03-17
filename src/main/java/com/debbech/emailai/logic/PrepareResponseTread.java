package com.debbech.emailai.logic;

import com.debbech.emailai.model.Task;
import com.debbech.emailai.model.WriteRequest;
import com.debbech.emailai.model.WriteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
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

        try {
            WriteResponse wresp = this.writeResponseFuture.get();
            if(wresp == null) return;

            Task task = new InMemoryStore().getOne(writeRequest.getName());
            task.setWriteResponse(wresp);
            new InMemoryStore().addOne(task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
