package com.example.sistemadefinancas.api.service;

import com.example.sistemadefinancas.domains.login.LoginRequest;
import com.example.sistemadefinancas.domains.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
