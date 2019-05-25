package com.example.james.suv.Empresa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.sql.Connection;

import com.example.james.suv.AcessoBD.ConnectionStr;
import com.example.james.suv.AcessoBD.Empresa;
import com.example.james.suv.R;

public class CadastroEmpresa extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnCadastrarEmpresa,btnCancelar;
    EditText idNomeEmpresa,idRazSocial,idCNPJ,idEnderecoEmpresa,idCEP,idTelefone,idUsuario,idEmail,idSenha;
    Spinner idEstado;
    Connection connect;
    Empresa emp= new Empresa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_empresa);

        btnCadastrarEmpresa=findViewById(R.id.btnCadastrarEmpresa);
        btnCancelar=findViewById(R.id.btnCancelar);
        idNomeEmpresa=findViewById(R.id.idNomeEmpresa);
        idRazSocial=findViewById(R.id.idRazSocial);
        idCNPJ=findViewById(R.id.idCNPJ);
        idEnderecoEmpresa=findViewById(R.id.idEnderecoEmpresa);
        idCEP=findViewById(R.id.idCEP);
        idEstado=findViewById(R.id.idEstado);
        idTelefone=findViewById(R.id.idTelefone);
        idUsuario=findViewById(R.id.idUsuario);
        idEmail=findViewById(R.id.idEmail);
        idSenha=findViewById(R.id.idSenha);

        Spinner spinner = findViewById(R.id.idEstado);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estado, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        ConnectionStr conStr=new ConnectionStr();
        connect =conStr.connectionclasss();

        btnCadastrarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emp.setNome(idNomeEmpresa.getText().toString());
                emp.setRaz_social(idRazSocial.getText().toString());
                emp.setCNPJ(idCNPJ.getText().toString());
                emp.setEndereco(idEnderecoEmpresa.getText().toString());
                emp.setCEP(idCEP.getText().toString());
                emp.setEstado(idEstado.getSelectedItem().toString());
                emp.setTelefone(idTelefone.getText().toString());
                emp.setUsuario(idUsuario.getText().toString());
                emp.setEmail(idEmail.getText().toString());
                emp.setSenha(idSenha.getText().toString());
                if(idUsuario.getText().toString().equals("")|| idSenha.getText().toString().equals("")||idEstado.getSelectedItem().toString().equals("Selecione")){
                    Toast.makeText(CadastroEmpresa.this, "O usuário, a senha e o estado são obrigatórios!", Toast.LENGTH_LONG).show();
                } else {
                    if (connect == null) {
                        Toast.makeText(CadastroEmpresa.this, "Verifique sua conexão com a internet!", Toast.LENGTH_LONG).show();
                    } else {
                        emp.CadastrarEmpresa();
                        try{
                            Intent m = new Intent(CadastroEmpresa.this, LoginEmpresa.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(m);
                            Toast.makeText(CadastroEmpresa.this, "Dados cadastro com sucesso!", Toast.LENGTH_LONG).show();
                        } catch(Exception e) {
                            Toast.makeText(CadastroEmpresa.this, "Erro no cadastro de dados!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(CadastroEmpresa.this, LoginEmpresa.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}