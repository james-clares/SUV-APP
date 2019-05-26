package com.example.james.suv.Cliente.Vacinas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.james.suv.AcessoBD.ListarVacinas;
import com.example.james.suv.Cliente.DetalhesVacinaCliente;
import com.example.james.suv.R;

public class VacinasDetalhes extends AppCompatActivity {

    TextView editNomeVacina,editPrimeiraDose,editSegundaDose,
            editTerceiraDose,editPrimeiroReforco,editSegundoReforco;
    TextView lblDetalhesPrimeiraDose,lblDetalhesSegundaDose,lblDetalhesTerceiraDose,lblDetalhesPrimeiroReforco,
            lblDetalhesSegundoReforco;
    Button btVoltar;
    long id;
    ListarVacinas list=new ListarVacinas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacinas_detalhes);

        editNomeVacina = findViewById(R.id.editNomeVacina);
        editPrimeiraDose = findViewById(R.id.editPrimeiraDose);
        editSegundaDose = findViewById(R.id.editSegundaDose);
        editTerceiraDose = findViewById(R.id.editTerceiraDose);
        editPrimeiroReforco = findViewById(R.id.editPrimeiroReforco);
        editSegundoReforco = findViewById(R.id.editSegundoReforco);
        lblDetalhesPrimeiraDose=findViewById(R.id.lblDetalhesPrimeiraDose);
        lblDetalhesSegundaDose=findViewById(R.id.lblDetalhesSegundaDose);
        lblDetalhesTerceiraDose=findViewById(R.id.lblDetalhesTerceiraDose);

        lblDetalhesPrimeiroReforco=findViewById(R.id.lblDetalhesPrimeiroReforco);
        lblDetalhesSegundoReforco=findViewById(R.id.lblDetalhesSegundoReforco);

        btVoltar = (Button) findViewById(R.id.btnVoltar);
        //list.carregarTodasVacinas();
        editNomeVacina.setText(list.getNome_vacina());
        editPrimeiraDose.setText(list.getData1());
        editSegundaDose.setText(list.getData2());
        editTerceiraDose.setText(list.getData3());
        editPrimeiroReforco.setText(list.getData4());
        editSegundoReforco.setText(list.getData5());

        lblDetalhesSegundaDose.setVisibility(View.GONE);
        lblDetalhesTerceiraDose.setVisibility(View.GONE);
        lblDetalhesPrimeiroReforco.setVisibility(View.GONE);
        lblDetalhesSegundoReforco.setVisibility(View.GONE);

        String segundadose=editSegundaDose.getText().toString();
        String terceiradose=editTerceiraDose.getText().toString();
        String primeiroreforco=editPrimeiroReforco.getText().toString();
        String segundoreforco=editSegundoReforco.getText().toString();

        if(segundadose.trim().equals("")){
            lblDetalhesSegundaDose.setVisibility(View.GONE);
        }
        else{
            lblDetalhesSegundaDose.setVisibility(View.VISIBLE);
        }

        if(terceiradose.trim().equals("")){
            lblDetalhesTerceiraDose.setVisibility(View.GONE);
        }
        else{
            lblDetalhesTerceiraDose.setVisibility(View.VISIBLE);
        }
        if(primeiroreforco.trim().equals("")){
            lblDetalhesPrimeiroReforco.setVisibility(View.GONE);
        }
        else{
            lblDetalhesPrimeiroReforco.setVisibility(View.VISIBLE);
        }

        if(segundoreforco.trim().equals("")){
            lblDetalhesSegundoReforco.setVisibility(View.GONE);
        }
        else{
            lblDetalhesSegundoReforco.setVisibility(View.VISIBLE);
        }

        lblDetalhesPrimeiraDose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prm="primeira";
                list.carregarDetalhesVacina(prm);
                Intent m = new Intent(VacinasDetalhes.this, DetalhesVacinaCliente.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        lblDetalhesSegundaDose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prm="segunda";
                list.carregarDetalhesVacina(prm);
                Intent m = new Intent(VacinasDetalhes.this, DetalhesVacinaCliente.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        lblDetalhesTerceiraDose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prm="terceira";
                list.carregarDetalhesVacina(prm);
                Intent m = new Intent(VacinasDetalhes.this, DetalhesVacinaCliente.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        lblDetalhesPrimeiroReforco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prm="1reforco";
                list.carregarDetalhesVacina(prm);
                Intent m = new Intent(VacinasDetalhes.this, DetalhesVacinaCliente.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        lblDetalhesSegundoReforco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prm="2reforco";
                list.carregarDetalhesVacina(prm);
                Intent m = new Intent(VacinasDetalhes.this, DetalhesVacinaCliente.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}