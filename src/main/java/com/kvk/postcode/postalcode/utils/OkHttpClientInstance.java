package com.kvk.postcode.postalcode.utils;

import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class OkHttpClientInstance {
    private static OkHttpClient client;

    private OkHttpClientInstance() {
    }

    public static OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient()
                    .newBuilder()
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .build();
        }
        return client;
    }
}
