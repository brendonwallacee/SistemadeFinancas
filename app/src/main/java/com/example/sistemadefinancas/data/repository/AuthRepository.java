package com.example.sistemadefinancas.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.sistemadefinancas.data.model.LoginRequest;
import com.example.sistemadefinancas.data.model.LoginResponse;
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

        api.login(new LoginRequest(email, password)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(Resource.success(response.body()));
                } else {
                    result.setValue(Resource.error("Credenciais inválidas"));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                result.setValue(Resource.error("Erro de conexão: " + t.getMessage()));
            }
        });
    }
}