package com.example.james.suv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.suv.AcessoBD.Empresa;

public class ModificarContato extends AppCompatActivity {

    EditText tNome,tEmail;
    TextView tID;
    Button btAtualizar,btExcluir,btVoltar;
    long id;

    //SQLController dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_contato);
        try {
            //dbcon = new SQLController(this);
            //dbcon.open();
            tNome = (EditText) findViewById(R.id.editNomeAtz);
            tEmail = (EditText) findViewById(R.id.editMailAtz);
            tID = (TextView) findViewById(R.id.tvID);

            btAtualizar = (Button) findViewById(R.id.btnAtualizar);
            btExcluir = (Button) findViewById(R.id.btnExcluir);
            btVoltar = (Button) findViewById(R.id.btnVoltar);

            Intent i = getIntent();
            String memberID = i.getStringExtra("memberID");
            String memberName = i.getStringExtra("memberName");
            String memberEmail = i.getStringExtra("memberEmail");

            id = Long.parseLong(memberID);

            tID.setText(memberID);
            tNome.setText(memberName);
            tEmail.setText(memberEmail);

            btExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //dbcon.excluirRegistro(id);
                    Empresa emp=new Empresa();
                    emp.deletarCliente(id);
                    //voltarPrincipal();
                    if(emp.deletarCliente(id)==true){
                        voltarPrincipal();
                        Toast.makeText(ModificarContato.this, "Cliente deletado", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(ModificarContato.this, "Erro ao deletar cliente", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            btAtualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String memName_upd = tNome.getText().toString();
                    String memEmail_upd = tEmail.getText().toString();
                    //dbcon.atualizarRegistro(id, memName_upd, memEmail_upd);
                    voltarPrincipal();
                }
            });
            btVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        catch(Exception e){
            Log.e("ModificarContato",e.toString());
        }
    }

    private void voltarPrincipal() {
        //Retorna para a View contendo todos os registros cadastrados
        Intent pi=new Intent(getApplicationContext(),Principal.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(pi);
    }
}
