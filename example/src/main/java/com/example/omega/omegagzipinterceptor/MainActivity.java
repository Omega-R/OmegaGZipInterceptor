package com.example.omega.omegagzipinterceptor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.omegagzipinterceptorlib.AcceptGzipInterceptor;
import com.example.omegagzipinterceptorlib.UngzippingInterceptor;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(new AcceptGzipInterceptor())
                .addInterceptor(new UngzippingInterceptor())
                .build();

    }
}
