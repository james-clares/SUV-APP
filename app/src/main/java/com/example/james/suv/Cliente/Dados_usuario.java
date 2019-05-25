package com.example.james.suv.Cliente;

import android.app.Fragment;
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

import com.example.james.suv.AcessoBD.Cliente;
import com.example.james.suv.R;

public class Dados_usuario extends Fragment implements AdapterView.OnItemSelectedListener {

    public Dados_usuario() {
    }
    Button btnAlterar;
    EditText idNome,idSobreNome,idEmail,idDataNasc,idNacionalidade,idRG,
            idCPF,idEndereco,idCEP,idTelefone,idTelefoneCel;
    Spinner idGenero;
    Cliente cli=new Cliente();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_dados_usuario, container, false);
        btnAlterar =(Button) rootView.findViewById(R.id.btnAlterar);

        Spinner spinner = rootView.findViewById(R.id.idGenero);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.genero, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        idNome=rootView.findViewById(R.id.idNome);
        idSobreNome=rootView.findViewById(R.id.idSobreNome);
        idEmail=rootView.findViewById(R.id.idEmail);
        idGenero=rootView.findViewById(R.id.idGenero);
        idDataNasc=rootView.findViewById(R.id.idDataNasc);
        idNacionalidade=rootView.findViewById(R.id.idNacionalidade);
        idRG=rootView.findViewById(R.id.idRG);
        idCPF=rootView.findViewById(R.id.idCPF);
        idEndereco=rootView.findViewById(R.id.idEndereco);
        idCEP=rootView.findViewById(R.id.idCEP);
        idTelefone=rootView.findViewById(R.id.idTelefone);
        idTelefoneCel=rootView.findViewById(R.id.idTelefoneCel);

        idNome.setText(cli.getNome());
        idSobreNome.setText(cli.getSobreNome());
        idEmail.setText(cli.getEmail());
        //idGenero.getSelectedItem();
        idDataNasc.setText(cli.getDataNasc());
        idNacionalidade.setText(cli.getNacionalidade());
        idRG.setText(cli.getRG());
        idCPF.setText(cli.getCPF());
        idEndereco.setText(cli.getEndereco());
        idCEP.setText(cli.getCEP());
        idTelefone.setText(cli.getTelefone());
        idTelefoneCel.setText(cli.getTelefone_cel());

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cli.setNome(idNome.getText().toString());
                cli.setSobreNome(idSobreNome.getText().toString());
                cli.setEmail(idEmail.getText().toString());
                cli.setSexo(idGenero.getSelectedItem().toString());
                cli.setDataNasc(idDataNasc.getText().toString());
                cli.setNacionalidade(idNacionalidade.getText().toString());
                cli.setRG(idRG.getText().toString());
                cli.setCPF(idCPF.getText().toString());
                cli.setEndereco(idEndereco.getText().toString());
                cli.setCEP(idCEP.getText().toString());
                cli.setTelefone(idTelefone.getText().toString());
                cli.setTelefone_cel(idTelefoneCel.getText().toString());
                cli.AlterarCliente();

                if(idGenero.getSelectedItem().equals("Selecione")){
                    Toast.makeText(getActivity(), "Selecione o gênero", Toast.LENGTH_LONG).show();
                }
                else {
                    if (cli.AlterarCliente() == true) {
                        //Intent m = new Intent(getActivity(), Perfil.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        //startActivity(m);
                        Toast.makeText(getActivity(), "Dados alterados com sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Erro na alteração dos dados", Toast.LENGTH_LONG).show();
                    }
                }
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