package com.debbech.emailai.model;


public class WriteResponse {

    private String plainResponse;
    private String ReqName;

    @Override
    public String toString() {
        return "WriteResponse{" +
                "plainResponse='" + plainResponse + '\'' +
                ", ReqName='" + ReqName + '\'' +
                '}';
    }

    public String getPlainResponse() {
        return plainResponse;
    }

    public void setPlainResponse(String plainResponse) {
        this.plainResponse = plainResponse;
    }

    public String getReqName() {
        return ReqName;
    }

    public void setReqName(String reqName) {
        ReqName = reqName;
    }
}
