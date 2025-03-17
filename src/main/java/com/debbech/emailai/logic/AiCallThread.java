package com.debbech.emailai.logic;

import com.debbech.emailai.model.ModelRequest;
import com.debbech.emailai.model.ModelResponse;
import com.debbech.emailai.model.WriteRequest;
import com.debbech.emailai.model.WriteResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.*;
import java.util.concurrent.Callable;

public class AiCallThread implements Callable<WriteResponse> {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private WriteRequest writeRequest;

    public AiCallThread(WriteRequest writeRequest){
        this.writeRequest = writeRequest;
    }

    @Override
    public WriteResponse call() throws Exception {

        ModelRequest modelRequest = new ModelRequest("llama3.2", "hello how are u", false);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(modelRequest);

        long startTimestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);

        String resp = this.doNetworkCall("192.168.1.10:11434", json);

        long endTimestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);

        if(resp != null) {

            ObjectMapper objectMapper = new ObjectMapper();
            ModelResponse responseObj = objectMapper.readValue(resp, ModelResponse.class);

            if(responseObj.isDone()){
                WriteResponse wres = new WriteResponse();
                wres.setPlainResponse(responseObj.getResponse());
                wres.setReqName(this.writeRequest.getName());
                wres.setResponseGeneratedAt(responseObj.getCreated_at());
                wres.setStartTs(startTimestamp);
                wres.setEndTs(endTimestamp);
                log.error(wres.toString());
                return wres;
            }
        }
        return null;
    }

    private String doNetworkCall(String aiHost, String json){

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofMinutes(5))  // Time to establish the connection
                .readTimeout(Duration.ofMinutes(5))     // Time to wait for the response
                .writeTimeout(Duration.ofMinutes(5))    // Time to send data (if applicable)
                .build();

        RequestBody rb = RequestBody.create(json,MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url("http://"+aiHost+"/api/generate") // Replace with your API
                .post(rb)
                .build();

        try (Response response = client.newCall(request).execute()) { // Auto-closes response
            if (response.isSuccessful() && response.body() != null) {
                //log.info("Response: {}", response.body().string());
                String l = response.body().string();
                return l;
            } else {
                log.error("Request failed with status: {}", response.code());
            }
        } catch (IOException e) {
            log.error("Request failed: {}", e.getMessage());
        }

        return null; //for errors
    }
}
