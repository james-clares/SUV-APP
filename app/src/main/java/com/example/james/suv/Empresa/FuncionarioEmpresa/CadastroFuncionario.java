package com.example.james.suv.Empresa.FuncionarioEmpresa;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.james.suv.AcessoBD.ConnectionStr;
import com.example.james.suv.AcessoBD.Empresa;
import com.example.james.suv.Empresa.PerfilEmpresa;
import com.example.james.suv.AcessoBD.Funcionario;
import com.example.james.suv.R;

import java.sql.Connection;

public class CadastroFuncionario extends Fragment implements AdapterView.OnItemSelectedListener {

    Button btnCadastrarFunc, btnVoltar;
    EditText idNomeFunc,idTipo,idIdade,idCPF,idSenha;
    Spinner idGenero;
    Funcionario func=new Funcionario();
    Connection connect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_cadastro_funcionario, container, false);

        Spinner spinner = rootView.findViewById(R.id.idGenero);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.genero, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        idNomeFunc=rootView.findViewById(R.id.idNomeFunc);
        idTipo=rootView.findViewById(R.id.idTipo);
        idGenero=rootView.findViewById(R.id.idGenero);
        idIdade=rootView.findViewById(R.id.idIdade);
        idCPF=rootView.findViewById(R.id.idCPF);
        idSenha=rootView.findViewById(R.id.idSenha);
        btnCadastrarFunc=rootView.findViewById(R.id.btnCadastrarFunc);
        btnVoltar=rootView.findViewById(R.id.btnVoltar);

        ConnectionStr conStr=new ConnectionStr();
        connect =conStr.connectionclasss();

        btnCadastrarFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Empresa emp=new Empresa();
                func.setId_emp(emp.getId());
                func.setNome(idNomeFunc.getText().toString());
                func.setTipo(idTipo.getText().toString());
                func.setGenero(idGenero.getSelectedItem().toString());
                func.setIdade(idIdade.getText().toString());
                func.setCPF(idCPF.getText().toString());
                func.setSenha(idSenha.getText().toString());
                if (idNomeFunc.getText().toString().equals("") || idSenha.getText().toString().equals("")||idGenero.getSelectedItem().toString().equals("Selecione")) {
                    Toast.makeText(getActivity(), "O usuário, a senha e o gênero são obrigatórios!!", Toast.LENGTH_LONG).show();
                }
                else {
                    if (connect == null) {
                        Toast.makeText(getActivity(), "Verifique sua conexão com a internet!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        func.CadastrarFuncionario();
                        try{
                            Intent m = new Intent(getActivity(), PerfilEmpresa.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(m);
                            Toast.makeText(getActivity(), "Dados cadastros com sucesso!", Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e){
                            Toast.makeText(getActivity(), "Erro no cadastro dos dados", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(getActivity(), PerfilEmpresa.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });
        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
