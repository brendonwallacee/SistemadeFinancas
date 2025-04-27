package com.example.sistemadefinancas.ui.recovery.mudar_senha;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sistemadefinancas.data.model.recovery.confirmar_token.ConfirmarTokenResponse;
import com.example.sistemadefinancas.data.repository.AuthRepository;
import com.example.sistemadefinancas.utils.Resource;

public class MudarSenhaViewModel extends ViewModel {
    private final MutableLiveData<Resource<ConfirmarTokenResponse>> confirmarTokenResult = new MutableLiveData<>();
    private final AuthRepository repository = new AuthRepository();

    public LiveData<Resource<ConfirmarTokenResponse>> getConfirmarTokenResult() {
        return confirmarTokenResult;
    }

    public void confirmarToken(String token, String novaSenha) {
        repository.confirmarToken(token, novaSenha, confirmarTokenResult);
    }
}
