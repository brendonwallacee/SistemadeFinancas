package com.example.sistemadefinancas.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.sistemadefinancas.R;
import com.example.sistemadefinancas.data.model.login.LoginResponse;
import com.example.sistemadefinancas.ui.cadastro.RegisterActivity;
import com.example.sistemadefinancas.ui.home.HomeActivity;
import com.example.sistemadefinancas.ui.recovery.mudar_senha.MudarSenhaActivity;
import com.example.sistemadefinancas.utils.Resource;


public class LoginActivity extends AppCompatActivity {

    private LoginViewModel viewModel;
    private EditText etLogin, etSenha;
    private Button btnLogin, btnTelaCadastro;

    private TextView txtForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        trazerEntradasDaTela();

        btnLogin.setOnClickListener(view -> login());

        btnTelaCadastro.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        txtForgotPassword.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, MudarSenhaActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void login() {
        String login = etLogin.getText().toString();
        String senha = etSenha.getText().toString();
        if (login.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha os campos", Toast.LENGTH_SHORT).show();
            return;
        }
        viewModel.login(login, senha);

        viewModel.getLoginResult().observe(this, result -> {
            if ((result.status == Resource.Status.SUCCESS)) {
                LoginResponse response = result.data;
                Toast.makeText(this, "Token: " + response.getToken(), Toast.LENGTH_SHORT).show();


                // INCLUIR SHARED PREFERENCES
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);


            } else if ((result.status == Resource.Status.ERROR)) {
                Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void trazerEntradasDaTela() {
        etLogin = findViewById(R.id.edLogin);
        etSenha = findViewById(R.id.etSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnTelaCadastro = findViewById(R.id.btnTelaCadastro);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }


}



