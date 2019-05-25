package com.example.james.suv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.james.suv.AcessoBD.Empresa;

public class Principal extends AppCompatActivity {

    Button btnAdicionar,btnSair;
    ListView lv;
    //SQLController dbcon;
    TextView memID_tv,memName_tv,memEmail_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //dbcon=new SQLController(this);
        //dbcon.open();
        lv=(ListView)findViewById(R.id.lvContatos);
        btnAdicionar=(Button)findViewById(R.id.btnAddContato);
        btnSair=(Button)findViewById(R.id.btnSair);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Principal.this,incluirContato.class);
                startActivity(it);
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finalizar();
            }
        });

        Empresa emp=new Empresa();
        //Cursor cursor=emp.CarregarDados();
        //ConnectionStr connect=new ConnectionStr();
        String[]from=new String[]{
                emp.getNome(),emp.getUsuario(),emp.getSenha()
                //DBHelper.CONTATO_ID,DBHelper.CONTATO_NOME,DBHelper.CONTATO_EMAIL

        };
        int[]to=new int[]{R.id.idID,R.id.idNome,R.id.idEmail};

        /*SimpleCursorAdapter adapter= new SimpleCursorAdapter(
                Principal.this,R.layout.activity_visualizar_contato,cursor,from,to);
        adapter.notifyDataSetChanged();*/
        //lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Recupera valores do item selecionado:
                memID_tv=(TextView) view.findViewById(R.id.idID);
                memName_tv=(TextView)view.findViewById(R.id.idNome);
                memEmail_tv=(TextView)view.findViewById(R.id.idEmail);
                //Prepara Intent para envio dos dados para actvity de mofidicação:
                Intent modif=new Intent(getApplicationContext(),ModificarContato.class);
                modif.putExtra("memberID",memID_tv.toString());
                modif.putExtra("memberNome",memName_tv.toString());
                modif.putExtra("memberEmail",memEmail_tv.toString());
                //Aciona activity de modificações:
                startActivity(modif);
            }
        });
    }

    private void finalizar() {
        this.finish();
    }
}
