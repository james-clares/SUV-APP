package com.example.james.suv.Empresa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.suv.AcessoBD.Empresa;
import com.example.james.suv.Cliente.MainActivity;
import com.example.james.suv.Funcionario.LoginFuncionario;
import com.example.james.suv.R;

public class LoginEmpresa extends AppCompatActivity {
    Button btnLoginEmpresa;
    EditText idLoginEmpresa, idSenhaEmpresa;
    String usuario,senha;
    TextView btnCadastroEmpresa,btnFuncionario,btnSair;
    Empresa emp=new Empresa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_empresa);

        btnLoginEmpresa=findViewById(R.id.btnLoginEmpresa);
        btnCadastroEmpresa=findViewById(R.id.btnCadastroEmpresa);
        btnFuncionario=findViewById(R.id.btnFuncionario);
        btnSair=findViewById(R.id.btnSair);
        idLoginEmpresa = findViewById(R.id.idLoginEmpresa);
        idSenhaEmpresa = findViewById(R.id.idSenhaEmpresa);

        btnLoginEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = idLoginEmpresa.getText().toString();
                senha = idSenhaEmpresa.getText().toString();

                emp.Login(usuario, senha);
                if (usuario.trim().equals("") || senha.trim().equals("")) {
                    Toast.makeText(LoginEmpresa.this, "Insira seu usuário ou senha!", Toast.LENGTH_LONG).show();
                } else {
                    if (emp.Login(usuario, senha) == true) {
                        //Intent m = new Intent(LoginEmpresa.this, PerfilEmpresa.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Intent m = new Intent(LoginEmpresa.this, MenuEmpresa.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(m);
                        Toast.makeText(LoginEmpresa.this, "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginEmpresa.this, "Usuário ou senha incorretos!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnCadastroEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(LoginEmpresa.this, CadastroEmpresa.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        btnFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(LoginEmpresa.this, LoginFuncionario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(LoginEmpresa.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });
    }
}