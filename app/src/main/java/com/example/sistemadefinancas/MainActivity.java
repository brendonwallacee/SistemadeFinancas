package com.example.sistemadefinancas;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sistemadefinancas.api.retrofit.ApiClient;
import com.example.sistemadefinancas.api.service.AuthService;
import com.example.sistemadefinancas.domains.login.LoginRequest;
import com.example.sistemadefinancas.domains.login.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private EditText etLogin, etSenha;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        trazerEntradasDaTela();
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

        AuthService authService = ApiClient
                .getClient()
                .create(AuthService.class);
        LoginRequest request = new LoginRequest(login,senha);

        Call<LoginResponse> call = authService.login(request);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(MainActivity.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Falha no login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro na conexão", Toast.LENGTH_SHORT).show();
                Log.e("API_ERROR", "Erro na requisição", t);
            }
        });

    }

    private void trazerEntradasDaTela() {
        etLogin = findViewById(R.id.edLogin);
        etSenha = findViewById(R.id.etSenha);
        btnLogin = findViewById(R.id.btnLogin);
    }


}



