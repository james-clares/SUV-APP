package com.example.james.suv.Empresa;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.suv.AcessoBD.Empresa;
import com.example.james.suv.R;

public class AlterarSenhaEmpresa extends Fragment {
    Button btnAtualizarSenhaEmpresa,btnCancelarSenhaEmpresa;
    EditText idSenhaUsuarioAntigaEmpresa,idSenhaUsuarioNovaEmpresa;
    TextView lblNomeEmpresa;
    Empresa emp=new Empresa();
    String senhaatual,senhanova;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_alterar_senha_empresa, container, false);

        btnAtualizarSenhaEmpresa=rootView.findViewById(R.id.btnAtualizarSenhaEmpresa);
        btnCancelarSenhaEmpresa=rootView.findViewById(R.id.btnCancelarSenhaEmpresa);
        idSenhaUsuarioAntigaEmpresa=rootView.findViewById(R.id.idSenhaUsuarioAntigaEmpresa);
        idSenhaUsuarioNovaEmpresa=rootView.findViewById(R.id.idSenhaUsuarioNovaEmpresa);
        lblNomeEmpresa=rootView.findViewById(R.id.lblNomeEmpresa);

        lblNomeEmpresa.setText("Bem-vindo "+emp.getNome());

        btnAtualizarSenhaEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senhaatual = idSenhaUsuarioAntigaEmpresa.getText().toString();
                senhanova = idSenhaUsuarioNovaEmpresa.getText().toString();
                Toast.makeText(getActivity(), "Senha atual: "+emp.getSenha(), Toast.LENGTH_LONG).show();
                String senha=emp.getSenha();
                emp.AlterarSenha(senhanova);
                if (senhaatual.trim().equals(senha)) {
                    if(senhaatual.trim().equals(senhanova)){
                        Toast.makeText(getActivity(), "A nova senha não pode ser igual a senha atual!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        if (emp.AlterarSenha(senhanova) == true) {
                            Intent m = new Intent(getActivity(), PerfilEmpresa.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(m);
                            Toast.makeText(getActivity(), "Senha atualizada com sucesso!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "Erro na atualização de senha", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else {
                    Toast.makeText(getActivity(), "A nova senha não pode ser igual a senha atual!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelarSenhaEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(getActivity(), PerfilEmpresa.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        return rootView;
    }
}