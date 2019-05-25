package com.example.james.suv.Cliente;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.suv.AcessoBD.Cliente;
import com.example.james.suv.AcessoBD.ConnectionStr;
import com.example.james.suv.Funcionario.LoginFuncionario;
import com.example.james.suv.R;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText idLogin, txtSenha;
    TextView btnSair, btnCadastro,btnLoginEmpresa,btnEsqueciMinhaSenha;
    TextView lblLogin, idSenha;
    Connection connect;
    String usuario,senha;
    Cliente cli=new Cliente();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectionStr conStr=new ConnectionStr();
        connect =conStr.connectionclasss();
        if(connect==null){
            Toast.makeText(MainActivity.this, "Banco desconectado!", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(MainActivity.this, "Banco conectado!", Toast.LENGTH_LONG).show();

        btnLogin = findViewById(R.id.btnLogin);
        btnSair =  findViewById(R.id.btnSair);
        btnCadastro = findViewById(R.id.btnCadastro);
        btnLoginEmpresa = findViewById(R.id.btnLoginEmpresa);
        btnEsqueciMinhaSenha=findViewById(R.id.btnEsqueciMinhaSenha);
        idLogin =  findViewById(R.id.idLogin);
        txtSenha = findViewById(R.id.txtSenha);
        //lblLogin=findViewById(R.id.lblLogin);
        //idSenha=findViewById(R.id.idSenha);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario=idLogin.getText().toString();
                //senha=idSenha.getText().toString();
                senha=txtSenha.getText().toString();

                cli.Login(usuario,senha);

                if (usuario.trim().equals("")||senha.trim().equals("")){
                    Toast.makeText(MainActivity.this, "Insira seu usuário ou senha", Toast.LENGTH_LONG).show();
                    //lblLogin.setError("Digite o usuário");
                    //validarCampos();
                    //Intent m = new Intent(MainActivity.this, PerfilEmpresa.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //startActivity(m);
                }
                /*if(senha.isEmpty()){
                    idSenha.setError("Digite a senha");
                }*/
                else {
                    if (cli.Login(usuario, senha) == true) {
                        progressBar.setVisibility(View.VISIBLE);
                        //Intent m = new Intent(MainActivity.this, Perfil.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Intent m = new Intent(MainActivity.this, MenuCliente.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(m);
                        Toast.makeText(MainActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Usuário ou senha incorretos!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(MainActivity.this, Cadastro.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        btnLoginEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(MainActivity.this, LoginFuncionario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        btnEsqueciMinhaSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(MainActivity.this, EsqueciMinhaSenhaCliente.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(MainActivity.this);
                //alerta.setTitle("Deseja sair?");
                //alerta.setIcon(R.mipmap.ic_launcher)
                alerta.setMessage("Deseja realmente sair do aplicativo?")
                        .setCancelable(false)
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent m = new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(m);
                            }
                        })
                        .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                AlertDialog alertDialog=alerta.create();
                alertDialog.show();
            }
        });
    }

    private void validarCampos(){
        boolean validar=true;
        usuario=idLogin.getText().toString();
        senha=txtSenha.getText().toString();
        if(usuario.isEmpty()){
            lblLogin.setError("Preencha o nome");
            validar=false;
        }
        else{

        }

        if(validar){
            Toast.makeText(MainActivity.this,"Sucesso",Toast.LENGTH_SHORT).show();
        }

    }
}