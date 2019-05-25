package com.example.james.suv.Funcionario.Vacinas;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.suv.AcessoBD.Cliente;
import com.example.james.suv.AcessoBD.ListarVacinas;
import com.example.james.suv.R;

import java.util.List;
import java.util.Map;

public class VacinaFuncionario extends AppCompatActivity {
    TextView TV_Header;
    Typeface font;
    ListView LV_Cli;
    SimpleAdapter ADAhere;
    TextView memID_tv,memName_tv,memEmail_tv;
    Button btnNovaVacina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacina_funcionario);

        TV_Header=(TextView)findViewById(R.id.TV_Header);
        LV_Cli=(ListView)findViewById(R.id.LV_Cli);
        btnNovaVacina=findViewById(R.id.btnNovaVacina);

        List<Map<String,String>> MyData = null;
        ListarVacinas mydata =new ListarVacinas();
        Cliente c=new Cliente();
        MyData= mydata.doInBackground(c.getId());
        c.CarregarDados();
        String[] fromwhere = { "ID","IDCliente","BCG_ID" };
        TV_Header.setText("Vacinas de "+c.getNome());
        int[] viewswhere = {R.id.lblIDVacina , R.id.lblIDCliente,R.id.lblBCG_ID};
        ADAhere = new SimpleAdapter(VacinaFuncionario.this, MyData,R.layout.activity_list_vacinas, fromwhere, viewswhere);
        LV_Cli.setAdapter(ADAhere);

        LV_Cli.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    memID_tv = (TextView) view.findViewById(R.id.lblIDVacina);
                    memName_tv = (TextView) view.findViewById(R.id.lblIDCliente);
                    memEmail_tv = (TextView) view.findViewById(R.id.lblBCG_ID);

                    String memberID_val=memID_tv.getText().toString();
                    String memberName_val=memName_tv.getText().toString();
                    String memberEmail_val=memEmail_tv.getText().toString();
                    //Prepara Intent para envio dos dados para actvity de mofidicação:
                    Intent modif = new Intent(VacinaFuncionario.this, AlterarVacinas.class);
                    modif.putExtra("memberID", memberID_val);
                    modif.putExtra("memberName", memberName_val);
                    modif.putExtra("memberEmail", memberEmail_val);
                    //Aciona activity de modificações:
                    //Toast.makeText(VacinaCliente.this, memberID_val, Toast.LENGTH_SHORT).show();
                    Toast.makeText(VacinaFuncionario.this, memberID_val, Toast.LENGTH_SHORT).show();
                    ListarVacinas list=new ListarVacinas();
                    list.setId_dose(memberID_val);
                    list.carregarTodasVacinas(memberID_val);
                    startActivity(modif);
                }
                catch(Exception e){
                    Log.e("Principal",e.toString());
                }
            }
        });

        btnNovaVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(VacinaFuncionario.this, NovaVacina.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });
    }
}