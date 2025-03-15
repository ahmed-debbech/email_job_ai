package com.debbech.emailai.logic;

import com.debbech.emailai.model.WriteRequest;
import com.debbech.emailai.model.WriteResponse;
import okhttp3.*;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.*;
import java.util.concurrent.Callable;

public class AiCallThread implements Callable<WriteResponse> {

    private WriteRequest writeRequest;

    public AiCallThread(WriteRequest writeRequest){
        this.writeRequest = writeRequest;
    }

    @Override
    public WriteResponse call() throws Exception {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofMinutes(5))  // Time to establish the connection
                .readTimeout(Duration.ofMinutes(5))     // Time to wait for the response
                .writeTimeout(Duration.ofMinutes(5))    // Time to send data (if applicable)
                .build();

        String json = """
                {
                  "model": "llama3.2",
                  "prompt": "Why is the sky blue?",
                  "stream": false
                }
                """;

        RequestBody rb = RequestBody.create(json,MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url("http://192.168.1.10:11434/api/generate") // Replace with your API
                .post(rb)
                .build();

        try (Response response = client.newCall(request).execute()) { // Auto-closes response
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Response: " + response.body().string());
            } else {
                System.out.println("Request failed with status: " + response.code());
            }
        } catch (IOException e) {
            System.err.println("Request failed: " + e.getMessage());
        }

        WriteResponse wres = new WriteResponse();
        wres.setPlainResponse("hello");
        wres.setReqName(this.writeRequest.getName());
        return wres;
    }
}
