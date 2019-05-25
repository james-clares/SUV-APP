package com.example.james.suv.Empresa;

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

import com.example.james.suv.AcessoBD.Empresa;
import com.example.james.suv.R;

public class DadosEmpresa extends Fragment implements AdapterView.OnItemSelectedListener {

    Button btnAlterarEmpresa,btnCancelar;
    EditText idNome,idRazSocial,idCNPJ,idEnderecoEmpresa,idCEP,idTelefone,idUsuario,idEmail;
    Spinner idEstado;
    Empresa emp=new Empresa();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_dados_empresa, container, false);

        btnAlterarEmpresa=rootView.findViewById(R.id.btnAlterarEmpresa);
        btnCancelar=rootView.findViewById(R.id.btnCancelar);
        idNome=rootView.findViewById(R.id.idNomeEmpresa);
        idRazSocial=rootView.findViewById(R.id.idRazSocial);
        idCNPJ=rootView.findViewById(R.id.idCNPJ);
        idEnderecoEmpresa=rootView.findViewById(R.id.idEnderecoEmpresa);
        idCEP=rootView.findViewById(R.id.idCEP);
        idEstado=rootView.findViewById(R.id.idEstado);
        idTelefone=rootView.findViewById(R.id.idTelefone);
        idUsuario=rootView.findViewById(R.id.idUsuario);
        idEmail=rootView.findViewById(R.id.idEmail);

        emp.CarregarDados();
        Spinner spinner = rootView.findViewById(R.id.idEstado);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.estado, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        idNome.setText(emp.getNome());
        idRazSocial.setText(emp.getRaz_social());
        idCNPJ.setText(emp.getCNPJ());
        idEnderecoEmpresa.setText(emp.getEndereco());
        idCEP.setText(emp.getCEP());
        //idEstado.setText(emp.getEstado());
        idTelefone.setText(emp.getTelefone());
        idUsuario.setText(emp.getUsuario());
        idEmail.setText(emp.getEmail());

        btnAlterarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emp.setNome(idNome.getText().toString());
                emp.setRaz_social(idRazSocial.getText().toString());
                emp.setCNPJ(idCNPJ.getText().toString());
                emp.setEndereco(idEnderecoEmpresa.getText().toString());
                emp.setCEP(idCEP.getText().toString());
                emp.setEstado(idEstado.getSelectedItem().toString());
                emp.setTelefone(idTelefone.getText().toString());
                emp.setUsuario(idUsuario.getText().toString());
                emp.setEmail(idEmail.getText().toString());

                emp.AlterarEmpresa();
                if(emp.AlterarEmpresa()==true){
                    Intent m = new Intent(getActivity(), PerfilEmpresa.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(m);
                    Toast.makeText(getActivity(), "Dados alterados com sucesso!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getActivity(), "Erro na alteração dos dados", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
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