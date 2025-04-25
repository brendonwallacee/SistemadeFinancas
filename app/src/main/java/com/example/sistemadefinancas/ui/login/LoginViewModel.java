package com.example.sistemadefinancas.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sistemadefinancas.data.model.LoginResponse;
import com.example.sistemadefinancas.data.repository.AuthRepository;
import com.example.sistemadefinancas.utils.Resource;

public class LoginViewModel extends ViewModel {
    private final MutableLiveData<Resource<LoginResponse>> loginResult = new MutableLiveData<>();
    private final AuthRepository repository = new AuthRepository();

    public LiveData<Resource<LoginResponse>> getLoginResult() {
        return loginResult;
    }

    public void login(String email, String password) {
        repository.login(email, password, loginResult);
    }
}
