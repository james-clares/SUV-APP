package com.example.james.suv;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class incluirContato extends AppCompatActivity {

    EditText nome,eMail;
    Button btnIncluir,btnVoltar;
    //SQLController dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incluir_contato);

        nome=(EditText)findViewById(R.id.editNome);
        eMail=(EditText)findViewById(R.id.editMail);

        Button btnIncluir=(Button)findViewById(R.id.btnIncluir);
        Button btnVoltar=(Button)findViewById(R.id.btnVoltar);

        //dbcon=new SQLController(this);
        //dbcon.open();

        btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNome=nome.getText().toString();
                String strEmail=eMail.getText().toString();
                //dbcon.inserirRegistro(strNome,strEmail);
                Intent m=new Intent(incluirContato.this,Principal.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mensagemExibir("inclusão","Contato adicionado com sucesso!");
                startActivity(m);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mensagemExibir(String titulo, String texto) {
        AlertDialog.Builder mensagem=new AlertDialog.Builder(incluirContato.this);
        mensagem.setTitle(titulo);
        mensagem.setMessage(texto);
        mensagem.setNeutralButton("OK",null);
        mensagem.show();
    }
}