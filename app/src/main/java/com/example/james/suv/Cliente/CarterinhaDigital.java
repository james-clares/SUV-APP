package com.example.james.suv.Cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.james.suv.AcessoBD.Cliente;
import com.example.james.suv.R;

public class CarterinhaDigital extends AppCompatActivity {
    Button btnVoltar;
    TextView lblNomeUsuario;
    Cliente c=new Cliente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carterinha_digital);
        btnVoltar=findViewById(R.id.btnVoltar);
        lblNomeUsuario=findViewById(R.id.lblNomeUsuario);

        lblNomeUsuario.setText("Bem-vindo "+c.getNome());

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(CarterinhaDigital.this, Perfil.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });
    }
}