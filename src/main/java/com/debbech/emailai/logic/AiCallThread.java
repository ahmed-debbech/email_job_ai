package com.debbech.emailai.logic;

import com.debbech.emailai.model.WriteRequest;
import com.debbech.emailai.model.WriteResponse;

import java.util.Random;
import java.util.concurrent.Callable;

public class AiCallThread implements Callable<WriteResponse> {

    private WriteRequest writeRequest;

    public AiCallThread(WriteRequest writeRequest){
        this.writeRequest = writeRequest;
    }

    @Override
    public WriteResponse call() throws Exception {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.err.println("Running");

        WriteResponse wres = new WriteResponse();
        wres.setPlainResponse("hello");
        wres.setReqName(this.writeRequest.getName());
        return wres;
    }
}
