package com.example.james.suv.Funcionario.Vacinas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.james.suv.AcessoBD.Empresa;
import com.example.james.suv.AcessoBD.ListarVacinas;
import com.example.james.suv.Cliente.Vacinas.DetalhesVacina;
import com.example.james.suv.Principal;
import com.example.james.suv.R;

public class AlterarVacinas extends AppCompatActivity {

    EditText editNomeVacina,editNomeFuncionarioResp,editPrimeiraDose,editSegundaDose,
    editTerceiraDose,editPrimeiroReforco,editSegundoReforco;
    TextView tID,lblDetalhesPrimeiraDose,lblDetalhesSegundaDose,lblDetalhesTerceiraDose,lblDetalhesPrimeiroReforco,
            lblDetalhesSegundoReforco;
    Button btAtualizar,btExcluir,btVoltar;
    long id;
	ListarVacinas list=new ListarVacinas();

	public int tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_vacinas);

        editNomeVacina = findViewById(R.id.editNomeVacina);
        editNomeFuncionarioResp = findViewById(R.id.editNomeFuncionarioResp);
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
                Intent m = new Intent(AlterarVacinas.this, DetalhesVacina.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        lblDetalhesSegundaDose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prm="segunda";
                list.carregarDetalhesVacina(prm);
                Intent m = new Intent(AlterarVacinas.this, DetalhesVacina.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        lblDetalhesTerceiraDose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prm="terceira";
                list.carregarDetalhesVacina(prm);
                Intent m = new Intent(AlterarVacinas.this, DetalhesVacina.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });
		
		lblDetalhesPrimeiroReforco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prm="1reforco";
                list.carregarDetalhesVacina(prm);
                Intent m = new Intent(AlterarVacinas.this, DetalhesVacina.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });
		
		lblDetalhesSegundoReforco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prm="2reforco";
                list.carregarDetalhesVacina(prm);
                Intent m = new Intent(AlterarVacinas.this, DetalhesVacina.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        try {
            editNomeVacina = findViewById(R.id.editNomeVacina);
            editNomeFuncionarioResp = findViewById(R.id.editNomeFuncionarioResp);
            editPrimeiraDose = findViewById(R.id.editPrimeiraDose);
            editSegundaDose = findViewById(R.id.editSegundaDose);
            editTerceiraDose = findViewById(R.id.editTerceiraDose);
            editPrimeiroReforco = findViewById(R.id.editPrimeiroReforco);
            editSegundoReforco = findViewById(R.id.editSegundoReforco);

            tID = (TextView) findViewById(R.id.tvID);

            btAtualizar = (Button) findViewById(R.id.btnAtualizar);
            btExcluir = (Button) findViewById(R.id.btnExcluir);
            btVoltar = (Button) findViewById(R.id.btnVoltar);

            Intent i = getIntent();
            String memberID = i.getStringExtra("memberID");
            String memberName = i.getStringExtra("memberName");
            String memberEmail = i.getStringExtra("memberEmail");

            id = Long.parseLong(memberID);

            tID.setText(memberID);
            //editNomeVacina.setText(memberName);
            //editNomeFuncionarioResp.setText(memberEmail);
            //editNomeVacina.setText(memberEmail);

			
			//editNomeVacina.setText(list.getNome_vacina());
            //editNomeFuncionarioResp.setText(list.getId_func_fk());
            //editPrimeiraDose.setText(list.getData1());
            //editSegundaDose.setText(list.getData2());
            //editTerceiraDose.setText(list.getData3());
            //editPrimeiroReforco = findViewById(R.id.editPrimeiroReforco);
            //editSegundoReforco = findViewById(R.id.editSegundoReforco);
			
            btExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //dbcon.excluirRegistro(id);
                    Empresa emp = new Empresa();
                    emp.deletarCliente(id);
                    //voltarPrincipal();
                    /*if (emp.deletarCliente(id) == true) {
                        voltarPrincipal();
                        Toast.makeText(AlterarVacinas.this, "Vacina deletada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AlterarVacinas.this, "Erro ao deletar vacina", Toast.LENGTH_SHORT).show();
                    }*/
                }
            });

            btAtualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String memName_upd = editNomeVacina.getText().toString();
                    String memEmail_upd = editNomeFuncionarioResp.getText().toString();
                    //dbcon.atualizarRegistro(id, memName_upd, memEmail_upd);
                    //voltarPrincipal();

                    Intent pi=new Intent(AlterarVacinas.this,AtualizarVacina.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(pi);
                }
            });
            btVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {
            Log.e("ModificarContato", e.toString());
        }
    }

    public void voltarPrincipal(){
        Intent pi=new Intent(getApplicationContext(),Principal.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(pi);
    }
}
