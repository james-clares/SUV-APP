package com.example.james.suv.Cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.suv.AcessoBD.Cliente;
import com.example.james.suv.R;

public class EsqueciMinhaSenhaCliente extends AppCompatActivity {

    TextView btnAlterarSenha,btnCancelar;
    EditText idSenha,idCPF;
    String senha,cpf;
    Cliente c=new Cliente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_minha_senha_cliente);
        btnAlterarSenha=findViewById(R.id.btnAlterarSenha);
        btnCancelar=findViewById(R.id.btnCancelar);
        idCPF=findViewById(R.id.idCPF);
        idSenha=findViewById(R.id.idSenha);

        btnAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf = idCPF.getText().toString();
                senha = idSenha.getText().toString();

                c.VerificaCPF(cpf);

                if (c.VerificaCPF(cpf) == true) {
                    c.RecuperarSenha(cpf, senha);
                    if (c.RecuperarSenha(cpf, senha) == true) {
                        Intent m = new Intent(EsqueciMinhaSenhaCliente.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(m);
                        Toast.makeText(EsqueciMinhaSenhaCliente.this, "Senha recuperada com sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(EsqueciMinhaSenhaCliente.this, "Erro na recuperação de senha", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(EsqueciMinhaSenhaCliente.this, "O CPF informado não existe na base de dados.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(EsqueciMinhaSenhaCliente.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });
    }
}
