package com.example.james.suv.Funcionario;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.suv.AcessoBD.Funcionario;
import com.example.james.suv.R;

public class AlterarSenhaFuncionario extends Fragment {

    Button btnAtualizarSenha,btnCancelarSenha;
    EditText idSenhaFuncAntiga,idSenhaFuncNova;
    TextView lblNomeFunc;
    Funcionario func=new Funcionario();
    String senhaatual,senhanova;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_alterar_senha_funcionario, container, false);

        btnAtualizarSenha=rootView.findViewById(R.id.btnAtualizarSenha);
        btnCancelarSenha=rootView.findViewById(R.id.btnCancelarSenha);
        idSenhaFuncAntiga=rootView.findViewById(R.id.idSenhaFuncAntiga);
        idSenhaFuncNova=rootView.findViewById(R.id.idSenhaFuncNova);
        lblNomeFunc=rootView.findViewById(R.id.lblNomeFunc);

        lblNomeFunc.setText("Bem-vindo "+func.getNome());

        btnAtualizarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senhaatual = idSenhaFuncAntiga.getText().toString();
                senhanova = idSenhaFuncNova.getText().toString();
                //Toast.makeText(getActivity(), "Senha atual: "+func.getSenha(), Toast.LENGTH_LONG).show();
                String senha=func.getSenha();
                func.AlterarSenha(senhanova);
                if (senhaatual.trim().equals(senha)) {
                    //Toast.makeText(AlterarSenhaUsuario.this, "A nova senha não pode ser igual a senha antiga!", Toast.LENGTH_LONG).show();
                    if(senhaatual.trim().equals(senhanova)){
                        Toast.makeText(getActivity(), "A nova senha não pode ser igual a senha atual!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        if (func.AlterarSenha(senhanova) == true) {
                            //Intent m = new Intent(getActivity(), Perfil.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            //startActivity(m);
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

        btnCancelarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent m = new Intent(getActivity(), Perfil.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(m);
            }
        });
        return rootView;
    }
}
