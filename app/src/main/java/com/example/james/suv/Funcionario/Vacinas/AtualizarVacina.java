package com.example.james.suv.Funcionario.Vacinas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.suv.AcessoBD.Cliente;
import com.example.james.suv.AcessoBD.Funcionario;
import com.example.james.suv.AcessoBD.ListarVacinas;
import com.example.james.suv.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AtualizarVacina extends AppCompatActivity {

    Spinner cmbDose;
    EditText idData,idLocal,idRegProf;
    Button btnAtualizarVacina,btnCancelar;
    String dose[]={"Selecione","2º Dose","3º Dose","1º Reforço","2º Reforço"};
    ArrayAdapter<String> adapter3;
    TextView idNomeVacina,idNomeCliente;

    Funcionario func=new Funcionario();
    ListarVacinas listvacinas = new ListarVacinas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_vacina);

        cmbDose = findViewById(R.id.cmbDose);

        idNomeVacina=findViewById(R.id.idNomeVacina);
        idNomeCliente=findViewById(R.id.idNomeCliente);
        idData = findViewById(R.id.idData);
        idLocal = findViewById(R.id.idLocal);
        idRegProf = findViewById(R.id.idRegProf);
        btnAtualizarVacina = findViewById(R.id.btnAtualizarVacina);
        btnCancelar = findViewById(R.id.btnCancelar);

        Date dataatual=new Date();
        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String dataform=dateFormat.format(dataatual);

        idData.setText(dataform);

        idNomeVacina.setText(listvacinas.getNome_vacina());
        Cliente c=new Cliente();
        idNomeCliente.setText(c.getNome());
        adapter3 = new ArrayAdapter<String>(AtualizarVacina.this, android.R.layout.simple_spinner_item, dose);

        cmbDose.setAdapter(adapter3);

        btnAtualizarVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vacina =idNomeVacina.getText().toString();
                String nomecliente = idNomeCliente.getText().toString();
                String dose = cmbDose.getSelectedItem().toString();

                String dose_vacina = "";
                if (dose.trim().equals("2º Dose")) {
                    dose_vacina = "segunda_dose";
                } 
				else if (dose.trim().equals("3º Dose")) {
                    dose_vacina = "terceira_dose";
                }
				else if (dose.trim().equals("1º Reforço")) {
                    dose_vacina = "primeiro_reforco";
                }
				else if (dose.trim().equals("2º Reforço")) {
                    dose_vacina = "segundo_reforco";
                }

                String id_vacina = listvacinas.carregarIdVacina(vacina);
                String id_cliente = listvacinas.carregarIdCliente(nomecliente);

                String data = idData.getText().toString();
                String local = idLocal.getText().toString();
                String refProf = idRegProf.getText().toString();

                if (cmbDose.getSelectedItem().toString().equals("Selecione")) {
                    Toast.makeText(AtualizarVacina.this, "Favor selecione o campo de dose.", Toast.LENGTH_LONG).show();
                } else {
                    if (listvacinas.AtualizarDose(id_vacina, id_cliente, dose_vacina, data, local, refProf) == true) {
                        Intent m = new Intent(AtualizarVacina.this, VacinaFuncionario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(m);
                        Toast.makeText(AtualizarVacina.this, "Dose da vacina atualizada!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(AtualizarVacina.this, "Erro na atualização", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}