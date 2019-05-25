package com.example.james.suv.Cliente;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.james.suv.AcessoBD.Cliente;
import com.example.james.suv.Cliente.Vacinas.VacinaCliente;
import com.example.james.suv.Cliente.Vacinas.VacinasRecentes;
import com.example.james.suv.GeradorQRCode;
import com.example.james.suv.LeitorQRCode;
import com.example.james.suv.R;

public class Perfil extends AppCompatActivity {

    TextView btnVacinas, btnDados, btnSairPerfil,btnAlterarSenha,btnCarterinha,btnQRCode,btnLeitorQRCode,btnListaVacinas;
    TextView idUsuarioNome;
    Cliente c=new Cliente();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        btnVacinas = findViewById(R.id.btnVacinas);
        btnDados = findViewById(R.id.btnDados);
        btnSairPerfil = findViewById(R.id.btnSairPerfil);
        btnAlterarSenha=findViewById(R.id.btnAlterarSenha);
        btnCarterinha=findViewById(R.id.btnCarterinha);
        btnQRCode=findViewById(R.id.btnQRCode);
        btnLeitorQRCode=findViewById(R.id.btnLeitorQRCode);
        btnListaVacinas=findViewById(R.id.btnListaVacinas);
        idUsuarioNome=findViewById(R.id.idUsuarioNome);

        idUsuarioNome.setText("Bem-vindo "+c.getNome());

        btnVacinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(Perfil.this, VacinasRecentes.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });
        btnDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(Perfil.this, Dados_usuario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);

            }
        });
        btnAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(Perfil.this, AlterarSenhaUsuario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        btnCarterinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(Perfil.this, CarterinhaDigital.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        btnQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(Perfil.this, GeradorQRCode.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        btnLeitorQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(Perfil.this, LeitorQRCode.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        btnListaVacinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(Perfil.this, VacinaCliente.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        btnSairPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(Perfil.this);
                alerta.setMessage("Deseja realmente sair?")
                        .setCancelable(false)
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent m = new Intent(Perfil.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(m);
                            }
                        });
                AlertDialog alertDialog=alerta.create();
                alertDialog.show();
            }
        });
    }
}