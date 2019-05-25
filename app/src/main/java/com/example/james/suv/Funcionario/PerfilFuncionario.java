package com.example.james.suv.Funcionario;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.james.suv.AcessoBD.Funcionario;
import com.example.james.suv.Funcionario.Vacinas.NovaVacina;
import com.example.james.suv.R;

public class PerfilFuncionario extends AppCompatActivity {

    TextView lblDadosFunc,lblNovaVacina,lblAlterarSenha,lblFunc,lblVacinasRecentes,lblSair;
    Funcionario func=new Funcionario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_funcionario);
        lblDadosFunc=findViewById(R.id.lblDadosFunc);
        lblNovaVacina=findViewById(R.id.lblNovaVacina);
        lblAlterarSenha=findViewById(R.id.lblAlterarSenha);
        lblVacinasRecentes=findViewById(R.id.lblVacinasRecentes);
        lblSair=findViewById(R.id.lblSair);


        lblFunc=findViewById(R.id.lblFunc);

        lblFunc.setText("Bem-vindo "+func.getNome());

        lblDadosFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(PerfilFuncionario.this, DadosFuncionario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });
        lblNovaVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(PerfilFuncionario.this, NovaVacina.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });
        lblAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(PerfilFuncionario.this, AlterarSenhaFuncionario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });
        lblVacinasRecentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lblSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(PerfilFuncionario.this);
                alerta.setMessage("Deseja realmente sair?")
                        .setCancelable(false)
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent m = new Intent(PerfilFuncionario.this, PerfilFuncionario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(m);
                            }
                        })
                        .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent m = new Intent(PerfilFuncionario.this, LoginFuncionario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(m);
                            }
                        });
                AlertDialog alertDialog=alerta.create();
                alertDialog.show();
            }
        });
    }
}