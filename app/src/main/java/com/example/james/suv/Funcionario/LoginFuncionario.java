package com.example.james.suv.Funcionario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.suv.AcessoBD.Funcionario;
import com.example.james.suv.Cliente.MainActivity;
import com.example.james.suv.R;

public class LoginFuncionario extends AppCompatActivity {

    TextView btnEntrar,lblVoltar;
    EditText idLoginFunc,idSenhaFunc;
    String usuario,senha;
    Funcionario func=new Funcionario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_funcionario);
        btnEntrar=findViewById(R.id.btnEntrar);
        lblVoltar=findViewById(R.id.lblVoltar);
        idLoginFunc=findViewById(R.id.idLoginFunc);
        idSenhaFunc=findViewById(R.id.idSenhaFunc);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario=idLoginFunc.getText().toString();
                senha=idSenhaFunc.getText().toString();
                func.Login(usuario,senha);
                if (usuario.trim().equals("") || senha.trim().equals("")){
                    Toast.makeText(LoginFuncionario.this, "Insira seu usuário ou senha", Toast.LENGTH_LONG).show();
                }
                else {
                    if (func.Login(usuario, senha) == true) {
                        //Intent m = new Intent(LoginFuncionario.this, PerfilFuncionario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Intent m = new Intent(LoginFuncionario.this, MenuFuncionario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(m);
                        Toast.makeText(LoginFuncionario.this, "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginFuncionario.this, "Usuário ou senha incorretos!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        lblVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(LoginFuncionario.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });
    }
}