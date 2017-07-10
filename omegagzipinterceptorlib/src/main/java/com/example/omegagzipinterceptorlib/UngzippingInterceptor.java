package com.example.omegagzipinterceptorlib;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.internal.http.RealResponseBody;
import okio.GzipSource;
import okio.Okio;

/**
 * Created by Omega on 7/10/2017.
 */

public class UngzippingInterceptor implements Interceptor {

    private static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    private static final String HEADER_CONTENT_LENGTH = "Content-Length";

    private static final String HEADER_GZIP = "gzip";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return ungzip(response);
    }

    private Response ungzip(final Response response) throws IOException {

        if (response.body() == null || !isGzipped(response)) {
            return response;
        }

        GzipSource responseBody = new GzipSource(response.body().source());
        Headers strippedHeaders = response.headers().newBuilder()
                .removeAll(HEADER_CONTENT_ENCODING)
                .removeAll(HEADER_CONTENT_LENGTH)
                .build();
        return response.newBuilder()
                .headers(strippedHeaders)
                .body(new RealResponseBody(strippedHeaders, Okio.buffer(responseBody)))
                .build();
    }

    private boolean isGzipped(Response response) {
        Headers headers = response.headers();

        for (int i = 0; i < headers.size(); i++) {
            String name = headers.name(i);
            String value = headers.value(i);
            if (HEADER_CONTENT_ENCODING.equalsIgnoreCase(name) && HEADER_GZIP.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

}