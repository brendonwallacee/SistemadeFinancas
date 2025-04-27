package com.example.sistemadefinancas.data.model.recovery.confirmar_token;

@SuppressWarnings("unused")
public class ConfirmarTokenRequest {

    String token, novaSenha;

    public ConfirmarTokenRequest(String token, String novaSenha) {
        this.token = token;
        this.novaSenha = novaSenha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }
}
