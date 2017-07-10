package com.example.omegagzipinterceptorlib;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Omega on 7/10/2017.
 */

public class AcceptGzipInterceptor implements Interceptor {

    private static final String ACCEPT_ENCODING = "gzip,deflate";
    private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader(HEADER_ACCEPT_ENCODING, ACCEPT_ENCODING)
                .build();
        return chain.proceed(request);
    }
}