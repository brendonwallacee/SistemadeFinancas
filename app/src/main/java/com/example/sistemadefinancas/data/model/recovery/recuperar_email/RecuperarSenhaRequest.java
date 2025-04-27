package com.example.sistemadefinancas.data.model.recovery.recuperar_email;

public class RecuperarSenhaRequest {

    private String email;

    public RecuperarSenhaRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
