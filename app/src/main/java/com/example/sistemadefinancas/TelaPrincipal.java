package com.example.sistemadefinancas;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaPrincipal extends AppCompatActivity {
    private TextView lblNome;
    private ListView listBoletos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        vincularComponentes();
        mostrarNomeUsuario();

    }
    private void vincularComponentes(){
        lblNome = findViewById(R.id.lblNome);
    }

    private void mostrarNomeUsuario(){
        String nome = getIntent().getStringExtra("nome");

        if (nome != null){
            lblNome.setText("Olá, "+ nome);
        }else lblNome.setText("usuário não encontrado");
    }


}