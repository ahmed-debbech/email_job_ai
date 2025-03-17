package com.debbech.emailai.model;

public class Task {

    private WriteRequest writeRequest;
    private WriteResponse writeResponse;

    public Task(WriteRequest writeRequest){
        this.writeRequest = writeRequest;
    }

    @Override
    public String toString() {
        return "Task{" +
                "writeRequest=" + writeRequest +
                ", writeResponse=" + writeResponse +
                '}';
    }

    public WriteRequest getWriteRequest() {
        return writeRequest;
    }

    public void setWriteRequest(WriteRequest writeRequest) {
        this.writeRequest = writeRequest;
    }

    public WriteResponse getWriteResponse() {
        return writeResponse;
    }

    public void setWriteResponse(WriteResponse writeResponse) {
        this.writeResponse = writeResponse;
    }
}
