package com.example.sistemadefinancas.ui.recovery.mudar_senha;

import android.content.Intent;
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
import com.example.sistemadefinancas.ui.login.LoginActivity;
import com.example.sistemadefinancas.utils.Resource;

public class MudarSenhaActivity extends AppCompatActivity {
    private MudarSenhaViewModel viewModel;
    private EditText edtToken, edtNovaSenha;
    private Button btnMudarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mudar_senha);

        vincularComponentes();

        btnMudarSenha.setOnClickListener(view -> confirmarToken());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void vincularComponentes() {
        edtToken = findViewById(R.id.edtToken);
        edtNovaSenha = findViewById(R.id.edtNovaSenha);
        btnMudarSenha = findViewById(R.id.btnMudarSenha);

        viewModel = new ViewModelProvider(this).get(MudarSenhaViewModel.class);
    }

    private void confirmarToken() {
        String token = edtToken.getText().toString();
        String novaSenha = edtNovaSenha.getText().toString();

        if (token.isEmpty() || novaSenha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }
        viewModel.confirmarToken(token, novaSenha);

        viewModel.getConfirmarTokenResult().observe(this, result -> {
            if ((result.status == Resource.Status.SUCCESS)) {
                Toast.makeText(this, "Senha alterada com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MudarSenhaActivity.this, LoginActivity.class);
                startActivity(intent);

            } else if ((result.status == Resource.Status.ERROR)) {
                Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}