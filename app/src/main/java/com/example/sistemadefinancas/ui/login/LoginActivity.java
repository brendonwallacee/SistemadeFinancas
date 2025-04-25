package com.example.sistemadefinancas.ui.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.sistemadefinancas.R;
import com.example.sistemadefinancas.data.model.LoginResponse;
import com.example.sistemadefinancas.utils.Resource;



public class LoginActivity extends AppCompatActivity {

    private LoginViewModel viewModel;
    private EditText etLogin, etSenha;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        trazerEntradasDaTela();
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        btnLogin.setOnClickListener(view -> login());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void login() {
        String login = etLogin.getText().toString();
        String senha = etSenha.getText().toString();
        System.out.printf(login);
        System.out.printf(senha);
        viewModel.login(login, senha);

        viewModel.getLoginResult().observe(this, result -> {
            if ((result.status == Resource.Status.SUCCESS)) {
                LoginResponse response = result.data;
                Toast.makeText(this, "Token: " + response.getToken(), Toast.LENGTH_SHORT).show();

                // INCLUIR SHARED PREFERENCES E REDIRECIONAR ASSIM QUE DER
            } else if ((result.status == Resource.Status.ERROR)) {
                Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show();
                System.out.println(result.message);
            }
        });
    }

    private void trazerEntradasDaTela() {
        etLogin = findViewById(R.id.edLogin);
        etSenha = findViewById(R.id.etSenha);
        btnLogin = findViewById(R.id.btnLogin);
    }


}



