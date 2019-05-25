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
import java.util.ArrayList;
import java.util.Date;

public class NovaVacina extends AppCompatActivity {
    Spinner cmbVacina, cmbDose;
    EditText idData, idLocal, idRegProf;
    Button btnCadastrarVacina, btnCancelar;
    String dose[] = {"Selecione", "1º Dose", "2º Dose", "3º Dose", "1º Reforço", "2º Reforço"};
    ArrayAdapter<String> adapter, adapter3;
    TextView idNomeCliente;

    Funcionario func = new Funcionario();
    ListarVacinas Listvacinas = new ListarVacinas();
    Cliente c=new Cliente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_vacina);

        cmbVacina = findViewById(R.id.cmbVacina);
        idNomeCliente=findViewById(R.id.idNomeCliente);
        cmbDose = findViewById(R.id.cmbDose);

        idData = findViewById(R.id.idData);
        idLocal = findViewById(R.id.idLocal);
        idRegProf = findViewById(R.id.idRegProf);
        btnCadastrarVacina = findViewById(R.id.btnCadastrarVacina);
        btnCancelar = findViewById(R.id.btnCancelar);

        Date dataatual=new Date();
        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String dataform=dateFormat.format(dataatual);

        idData.setText(dataform);
        ArrayList<String> vacinas = func.CarregarVacinas();

        adapter = new ArrayAdapter<String>(NovaVacina.this, android.R.layout.simple_spinner_item, vacinas);
        idNomeCliente.setText(c.getNome());
        adapter3 = new ArrayAdapter<String>(NovaVacina.this, android.R.layout.simple_spinner_item, dose);

        //int a=func.CarregarListaVacinas();
        //Toast.makeText(getActivity(), "Nº Registros: "+a, Toast.LENGTH_LONG).show();
        cmbVacina.setAdapter(adapter);
        cmbDose.setAdapter(adapter3);
        //ArrayAdapter<CharSequence>adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaClientes);

        btnCadastrarVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vacina = cmbVacina.getSelectedItem().toString();
                String nomecliente = idNomeCliente.getText().toString();
                String dose = cmbDose.getSelectedItem().toString();

                String dose_vacina = "";
                if (dose.trim().equals("1º Dose")) {
                    dose_vacina = "primeira_dose";
                } else if (dose.trim().equals("2º Dose")) {
                    dose_vacina = "segunda_dose";

                } else if (dose.trim().equals("3º Dose")) {
                    dose_vacina = "terceira_dose";
                }
                //usuario.trim().equals("") || senha.trim().equals("")
                String id_vacina = Listvacinas.carregarIdVacina(vacina);
                String id_cliente = Listvacinas.carregarIdCliente(nomecliente);

                String data = idData.getText().toString();
                String local = idLocal.getText().toString();
                String refProf = idRegProf.getText().toString();

                if (cmbVacina.getSelectedItem().toString().equals("Selecione") ||
                        cmbDose.getSelectedItem().toString().equals("Selecione")) {
                    Toast.makeText(NovaVacina.this, "Favor selecione o campo de vacina, cliente ou dose.", Toast.LENGTH_LONG).show();
                } else {
                    if (Listvacinas.CadastrarVacina(id_vacina, id_cliente, dose_vacina, data, local, refProf) == true) {
                        Intent m = new Intent(NovaVacina.this, VacinaFuncionario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(m);
                        Toast.makeText(NovaVacina.this, "Dados cadastro com sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(NovaVacina.this, "Erro no cadastro dos dados", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(NovaVacina.this, VacinaFuncionario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });
    }
}