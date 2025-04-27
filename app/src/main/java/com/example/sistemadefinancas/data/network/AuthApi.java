package com.example.sistemadefinancas.data.network;

import com.example.sistemadefinancas.data.model.cadastro.RegisterRequest;
import com.example.sistemadefinancas.data.model.login.LoginRequest;
import com.example.sistemadefinancas.data.model.login.LoginResponse;
import com.example.sistemadefinancas.data.model.recovery.confirmar_token.ConfirmarTokenRequest;
import com.example.sistemadefinancas.data.model.recovery.recuperar_email.RecuperarSenhaRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("auth/registrar")
    Call<Void> registrar(@Body RegisterRequest request);

    @POST("auth/recuperar_senha")
    Call<Void> recuperarSenha(@Body RecuperarSenhaRequest request);

    @POST("auth/confirmar_token")
    Call<Void> confirmarToken(@Body ConfirmarTokenRequest request);
}
