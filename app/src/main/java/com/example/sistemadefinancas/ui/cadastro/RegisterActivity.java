package com.example.sistemadefinancas.ui.cadastro;

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
import androidx.lifecycle.ViewModelProvider;

import com.example.sistemadefinancas.R;
import com.example.sistemadefinancas.utils.Resource;

public class RegisterActivity extends AppCompatActivity {

    private RegisterViewModel viewModel;
    private EditText edtEmail, edtLogin, edtSenha;
    private Button btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        vincularComponentes();

        btnCadastro.setOnClickListener(view -> cadastrar());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    private void vincularComponentes(){
        edtEmail = findViewById(R.id.edtEmail);
        edtLogin = findViewById(R.id.edtLogin);
        edtSenha = findViewById(R.id.edtSenha);
        btnCadastro = findViewById(R.id.btnCadastro);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
    }

    private void cadastrar(){
        String email = edtEmail.getText().toString();
        String login = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();

        viewModel.registrar(email, login, senha);

        viewModel.getRegisterResult().observe(this, result -> {
            if ((result.status == Resource.Status.SUCCESS)) {
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else if ((result.status == Resource.Status.ERROR)) {
                Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show();
                Log.d("REGISTRO_DEBUG", "Erro: "+result.status);
                Log.d("REGISTRO_DEBUG", "Resultado: "+result.message);

            }
        });
    }
}