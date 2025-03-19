package com.example.sistemadefinanas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

import model.Usuario;
import repository.implementacao.UsuarioRepository;
import repository.interfaces.IUsuarioRepository;

public class MainActivity extends AppCompatActivity {


    private EditText txtEmail;
    private EditText txtSenha;
    private Button btnLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        vincularComponentes();
        criarEventoBotaoLogin();
        salvarUsuario(carregarUsuario());
    }

    private Usuario carregarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setEmail("brendonwallace2001@gmail.com");
        usuario.setNome("Brendon Wallace");
        usuario.setSenha("12346");
        return usuario;
    }

    private void salvarUsuario(Usuario usuario){
        IUsuarioRepository repository = new UsuarioRepository(MainActivity.this);
        repository.salvar(usuario);
    }

    private void vincularComponentes(){
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private boolean verificarLogin(String nome, String senha){
        Usuario usuario = carregarUsuario();
        String nomeLogin = usuario.getNome();
        String senhaLogin = usuario.getSenha();
        return Objects.equals(nome, nomeLogin) && Objects.equals(senha, senhaLogin);
    }

    private void criarEventoBotaoLogin(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();

                boolean okay = verificarLogin(nome, senha);

                Intent intent = new Intent(MainActivity.this, TelaPrincipal.class);
                if (okay)
                    intent.putExtra("nome", nome);

                startActivity(intent);
            }
        });
    }


}