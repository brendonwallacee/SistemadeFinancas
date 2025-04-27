package com.example.sistemadefinancas.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.sistemadefinancas.data.model.cadastro.RegisterRequest;
import com.example.sistemadefinancas.data.model.cadastro.RegisterResponse;
import com.example.sistemadefinancas.data.model.login.LoginRequest;
import com.example.sistemadefinancas.data.model.login.LoginResponse;
import com.example.sistemadefinancas.data.model.recovery.confirmar_token.ConfirmarTokenRequest;
import com.example.sistemadefinancas.data.model.recovery.confirmar_token.ConfirmarTokenResponse;
import com.example.sistemadefinancas.data.model.recovery.recuperar_email.RecuperarSenhaRequest;
import com.example.sistemadefinancas.data.model.recovery.recuperar_email.RecuperarSenhaResponse;
import com.example.sistemadefinancas.data.network.ApiClient;
import com.example.sistemadefinancas.data.network.AuthApi;
import com.example.sistemadefinancas.utils.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final AuthApi api;

    public AuthRepository() {
        this.api = ApiClient.getClient().create(AuthApi.class);
    }

    public void login(String email, String password, MutableLiveData<Resource<LoginResponse>> result) {
        result.setValue(Resource.loading());

        api.login(new LoginRequest(email, password)).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(Resource.success(response.body()));
                } else {
                    result.setValue(Resource.error("Credenciais inválidas"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                result.setValue(Resource.error("Erro de conexão: " + t.getMessage()));
            }
        });
    }

    public void registrar(String email, String login, String senha, MutableLiveData<Resource<RegisterResponse>> result) {
        result.setValue(Resource.loading());

        api.registrar(new RegisterRequest(email, login, senha)).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    result.setValue(Resource.success(null));
                } else {
                    result.setValue(Resource.error("Dados de cadastro inválidos, tente novamente"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                result.setValue(Resource.error("Erro de conexão: " + t.getMessage()));
            }
        });
    }

    public void recuperarSenha(String email, MutableLiveData<Resource<RecuperarSenhaResponse>> result) {
        result.setValue(Resource.loading());

        api.recuperarSenha(new RecuperarSenhaRequest(email)).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    result.setValue(Resource.success(null));
                } else {
                    result.setValue(Resource.error("Email não cadastrado no sistema"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                result.setValue(Resource.error("Erro de conexão: " + t.getMessage()));
            }
        });
    }

    public void confirmarToken(String token, String novaSenha, MutableLiveData<Resource<ConfirmarTokenResponse>> result) {
        result.setValue(Resource.loading());

        api.confirmarToken(new ConfirmarTokenRequest(token, novaSenha)).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    result.setValue(Resource.success(null));
                } else {
                    result.setValue(Resource.error("Token inválido"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                result.setValue(Resource.error("Erro de conexão: " + t.getMessage()));
            }
        });
    }
}