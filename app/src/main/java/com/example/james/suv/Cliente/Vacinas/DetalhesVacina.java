package com.example.james.suv.Cliente.Vacinas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.james.suv.AcessoBD.ListarVacinas;
import com.example.james.suv.Funcionario.Vacinas.AlterarVacinas;
import com.example.james.suv.R;

public class DetalhesVacina extends AppCompatActivity {
    EditText editNomeVacina,editNomeFunc,editData,editLocal,editRegistroProf;
    Button btnAtualizar,btnExcluir,btnVoltar;
    ListarVacinas list=new ListarVacinas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_vacina);
        editNomeVacina=findViewById(R.id.editNomeVacina);
        editNomeFunc=findViewById(R.id.editNomeFunc);
        editData=findViewById(R.id.editData);
        editLocal=findViewById(R.id.editLocal);
        editRegistroProf=findViewById(R.id.editRegistroProf);
        btnAtualizar=findViewById(R.id.btnAtualizar);
        btnExcluir=findViewById(R.id.btnExcluir);
        btnVoltar=findViewById(R.id.btnVoltar);

        editNomeVacina.setText(list.getNome_vacina());
        editNomeFunc.setText(list.getNome_func());
        editData.setText(list.getData());
        editLocal.setText(list.getLocal());
        editRegistroProf.setText(list.getRegistro_prof());

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pi=new Intent(getApplicationContext(),AlterarVacinas.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(pi);
            }
        });
    }
}
