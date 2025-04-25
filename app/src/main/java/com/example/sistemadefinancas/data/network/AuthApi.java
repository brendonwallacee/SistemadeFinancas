package com.example.sistemadefinancas.data.network;

import com.example.sistemadefinancas.data.model.LoginRequest;
import com.example.sistemadefinancas.data.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
