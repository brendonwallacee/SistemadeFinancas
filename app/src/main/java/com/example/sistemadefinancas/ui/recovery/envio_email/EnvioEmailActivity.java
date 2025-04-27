package com.example.sistemadefinancas.ui.recovery.envio_email;

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
import com.example.sistemadefinancas.ui.recovery.mudar_senha.MudarSenhaActivity;
import com.example.sistemadefinancas.utils.Resource;

public class EnvioEmailActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EnvioEmailViewModel viewModel;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_envio_email);


        vincularComponentes();

        btnEnviar.setOnClickListener(view -> enviarToken());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void vincularComponentes(){
        edtEmail = findViewById(R.id.edtEmail);
        btnEnviar = findViewById(R.id.btnEnviar);

        viewModel = new ViewModelProvider(this).get(EnvioEmailViewModel.class);
    }

    private void enviarToken(){
        String email = edtEmail.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(this, "Preencha o campo", Toast.LENGTH_SHORT).show();
            return;
        }
        viewModel.enviarEmail(email);

        viewModel.getRecuperarSenhaResult().observe(this, result -> {
            if ((result.status == Resource.Status.SUCCESS)) {
                Toast.makeText(this, "Email enviado!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EnvioEmailActivity.this, MudarSenhaActivity.class);
                startActivity(intent);

            } else if ((result.status == Resource.Status.ERROR)) {
                Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show();
            }
        });

    }
}