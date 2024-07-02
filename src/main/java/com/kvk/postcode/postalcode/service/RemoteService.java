package com.kvk.postcode.postalcode.service;

import com.kvk.postcode.postalcode.exception.HttpRequestException;
import com.kvk.postcode.postalcode.utils.OkHttpClientInstance;
import okhttp3.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Component
public class RemoteService {

    private final OkHttpClient okHttpClient = OkHttpClientInstance.getClient();

    @Value("${postal_code_rest_endpoint}")
    private String ENDPOINT;
    public String fetchPostalCodeDetails(String code) throws IOException, HttpRequestException {
        Request request = new Request.Builder()
                .url(ENDPOINT + code)
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        try(Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new HttpRequestException("Failed to load data for postal code " + code);
            }
        }
    }
}
