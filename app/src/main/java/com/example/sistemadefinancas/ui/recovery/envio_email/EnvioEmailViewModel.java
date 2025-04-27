package com.example.sistemadefinancas.ui.recovery.envio_email;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sistemadefinancas.data.model.recovery.recuperar_email.RecuperarSenhaResponse;
import com.example.sistemadefinancas.data.repository.AuthRepository;
import com.example.sistemadefinancas.utils.Resource;

public class EnvioEmailViewModel extends ViewModel {

    private final MutableLiveData<Resource<RecuperarSenhaResponse>> recuperarSenhaResult = new MutableLiveData<>();
    private final AuthRepository repository = new AuthRepository();

    public LiveData<Resource<RecuperarSenhaResponse>> getRecuperarSenhaResult() {
        return recuperarSenhaResult;
    }

    public void enviarEmail(String email) {
        repository.recuperarSenha(email, recuperarSenhaResult);
    }

}
