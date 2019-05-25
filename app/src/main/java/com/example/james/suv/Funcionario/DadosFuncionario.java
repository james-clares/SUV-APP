package com.example.james.suv.Funcionario;

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

import com.example.james.suv.AcessoBD.Funcionario;
import com.example.james.suv.R;

public class DadosFuncionario extends Fragment implements AdapterView.OnItemSelectedListener {

    Button btnAlterar ;
    EditText idNomeEmpresa,idNomeFunc,idTipo,idIdade,idCPF;
    Spinner idGenero;
    Funcionario func=new Funcionario();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_dados_funcionario, container, false);

        Spinner spinner = rootView.findViewById(R.id.idGenero);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.genero, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        idNomeEmpresa=rootView.findViewById(R.id.idNomeEmpresa);
        idNomeFunc=rootView.findViewById(R.id.idNomeFunc);
        idTipo=rootView.findViewById(R.id.idTipo);
        idGenero=rootView.findViewById(R.id.idGenero);
        idIdade=rootView.findViewById(R.id.idIdade);
        idCPF=rootView.findViewById(R.id.idCPF);
        btnAlterar=rootView.findViewById(R.id.btnAlterar);

        idNomeEmpresa.setText(func.getNome_empresa());
        idNomeFunc.setText(func.getNome());
        idTipo.setText(func.getTipo());
        //idGenero.setText(func.getGenero());
        idIdade.setText(func.getIdade());
        idCPF.setText(func.getCPF());

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                func.setNome(idNomeFunc.getText().toString());
                func.setTipo(idTipo.getText().toString());
                func.setGenero(idGenero.getSelectedItem().toString());
                func.setIdade(idIdade.getText().toString());
                func.setCPF(idCPF.getText().toString());

                func.AlterarFuncionario();
                if(func.AlterarFuncionario()==true){
                    //Intent m = new Intent(getActivity(), PerfilFuncionario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //startActivity(m);
                    Toast.makeText(getActivity(), "Dados alterados com sucesso!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getActivity(), "Erro na alteração dos dados", Toast.LENGTH_LONG).show();
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
