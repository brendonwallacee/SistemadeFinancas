package com.example.sistemadefinancas.ui.cadastro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sistemadefinancas.data.model.cadastro.RegisterResponse;
import com.example.sistemadefinancas.data.repository.AuthRepository;
import com.example.sistemadefinancas.utils.Resource;

public class RegisterViewModel extends ViewModel {

    private final MutableLiveData<Resource<RegisterResponse>> registerResult = new MutableLiveData<>();

    private final AuthRepository repository = new AuthRepository();

    public LiveData<Resource<RegisterResponse>> getRegisterResult() {
        return registerResult;
    }

    public void registrar(String email,String login, String senha) {
        repository.registrar(email, login, senha, registerResult);
    }
}
