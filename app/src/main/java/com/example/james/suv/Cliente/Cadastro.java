package com.example.james.suv.Cliente;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.james.suv.AcessoBD.Cliente;
import com.example.james.suv.AcessoBD.ConnectionStr;
import com.example.james.suv.R;

import java.sql.Connection;
import java.util.Calendar;

public class Cadastro extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Cliente c = new Cliente();
    EditText idNome, idSobreNome, idEmail, idDataNasc,idNacionalidade,idRG, idCPF,
            idEndereco,idCEP,idTelefone,idTelefoneCel,idSenha;
    Spinner idGenero;
    Button btnCadastrar, btnCancelar;
    Connection connect;
	private int dia, mes, ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCancelar = findViewById(R.id.btnCancelar);

        Spinner spinner = findViewById(R.id.idGenero);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genero, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        idNome = findViewById(R.id.idNome);
        idSobreNome = findViewById(R.id.idSobreNome);
        idEmail=findViewById(R.id.idEmail);
        idGenero = findViewById(R.id.idGenero);
        idDataNasc = findViewById(R.id.idDataNasc);
        idNacionalidade=findViewById(R.id.idNacionalidade);
        idRG = findViewById(R.id.idRG);
        idCPF = findViewById(R.id.idCPF);
        idEndereco=findViewById(R.id.idEndereco);
        idCEP = findViewById(R.id.idCEP);
        idTelefone = findViewById(R.id.idTelefone);
        idTelefoneCel = findViewById(R.id.idTelefoneCel);
        idSenha = findViewById(R.id.idSenha);

        ConnectionStr conStr=new ConnectionStr();
        connect =conStr.connectionclasss();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.setNome(idNome.getText().toString());
                c.setSobreNome(idSobreNome.getText().toString());
                c.setEmail(idEmail.getText().toString());
                c.setSexo(idGenero.getSelectedItem().toString());
                c.setDataNasc(idDataNasc.getText().toString());
                c.setNacionalidade(idNacionalidade.getText().toString());
                c.setRG(idRG.getText().toString());
                c.setCPF(idCPF.getText().toString());
                c.setEndereco(idEndereco.getText().toString());
                c.setCEP(idCEP.getText().toString());
                c.setTelefone(idTelefone.getText().toString());
                c.setTelefone_cel(idTelefoneCel.getText().toString());
                c.setSenha(idSenha.getText().toString());
                if (idNome.getText().toString().equals("") || idSenha.getText().toString().equals("")||idGenero.getSelectedItem().toString().equals("Selecione")) {
                    Toast.makeText(Cadastro.this, "O usuário, a senha e o gênero são obrigatórios!!", Toast.LENGTH_LONG).show();
                } else {
                    if(connect==null){
                        Toast.makeText(Cadastro.this, "Verifique sua conexão com a internet!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        String cpf=idCPF.getText().toString();
                        if(c.VerificaCPF(cpf)==true){
                            Toast.makeText(Cadastro.this, "Já existe um usuário cadastrado com este CPF", Toast.LENGTH_LONG).show();
                        }
                        else {
                            c.CadastrarCliente();
                            try {
                                Intent m = new Intent(Cadastro.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(m);
                                Toast.makeText(Cadastro.this, "Dados cadastro com sucesso!", Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(Cadastro.this, "Erro no cadastro dos dados", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(Cadastro.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        idDataNasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data();
            }
        });
    }

    /*public void onClick(View v) {
        if (v == idDataNasc) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    idDataNasc.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + ano);
                }
            }
                    , ano, mes, dia);
            datePickerDialog.show();
        }
    }*/

    public void data(){
        final Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        ano = c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                idDataNasc.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + ano);
            }
        }
                , ano, mes, dia);
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}