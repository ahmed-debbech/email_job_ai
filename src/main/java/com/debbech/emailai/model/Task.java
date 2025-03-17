package com.debbech.emailai.model;

public class Task {

    private WriteRequest writeRequest;
    private WriteResponse writeResponse;
    private Boolean failed;

    public Boolean getFailed() {
        return failed;
    }

    public void setFailed(Boolean failed) {
        this.failed = failed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "writeRequest=" + writeRequest +
                ", writeResponse=" + writeResponse +
                ", failed=" + failed +
                '}';
    }

    public Task(WriteRequest writeRequest){
        this.writeRequest = writeRequest;
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
