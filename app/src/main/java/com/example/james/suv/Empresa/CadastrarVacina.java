package com.example.james.suv.Empresa;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.james.suv.AcessoBD.Empresa;
import com.example.james.suv.R;

public class CadastrarVacina extends Fragment {

    EditText idNomeVacina;
    Button btnCadastrarVacina;
    Empresa emp=new Empresa();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_cadastrar_vacina, container, false);

        idNomeVacina=rootView.findViewById(R.id.idNomeVacina);
        btnCadastrarVacina=rootView.findViewById(R.id.btnCadastrarVacina);

        btnCadastrarVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idNomeVacina.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Preencha o nome da vacina", Toast.LENGTH_LONG).show();
                }
                else{
                    emp.CadastrarVacina(idNomeVacina.getText().toString());
                    try{
                        Toast.makeText(getActivity(), "Dados cadastros com sucesso!", Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e){
                        Toast.makeText(getActivity(), "Erro no cadastro!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return rootView;
    }
}
