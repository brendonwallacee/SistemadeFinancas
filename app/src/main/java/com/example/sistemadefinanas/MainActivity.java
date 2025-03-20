package com.example.sistemadefinanas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    private Usuario carregarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail("testedelogin@gmail.com");
        usuario.setNome("Teste");
        usuario.setSenha("123456");
        return usuario;
    }

    private void salvarUsuario(Usuario usuario) {
        IUsuarioRepository repository = new UsuarioRepository(MainActivity.this);
        repository.salvar(usuario);
    }

    private void vincularComponentes() {
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private boolean verificarLogin(String email, String senha, Usuario usuario) {
        String emailLogin = usuario.getEmail();
        String senhaLogin = usuario.getSenha();
        return Objects.equals(email, emailLogin) && Objects.equals(senha, senhaLogin);
    }

    private void criarEventoBotaoLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = carregarUsuario();

                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();


                Intent intent = new Intent(MainActivity.this, TelaPrincipal.class);
                if (verificarLogin(email, senha, usuario)) {
                    intent.putExtra("nome", usuario.getNome());
                    startActivity(intent);
                } else
                    Toast.makeText(MainActivity.this, "Cadastro não encontrado! Tente novamente", Toast.LENGTH_SHORT).show();

            }
        });
    }
}