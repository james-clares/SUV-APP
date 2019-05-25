package com.example.james.suv.Funcionario;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.suv.AcessoBD.Cliente;
import com.example.james.suv.AcessoBD.PesquisarCliente;
import com.example.james.suv.Funcionario.Vacinas.VacinaFuncionario;
import com.example.james.suv.R;

import java.util.List;
import java.util.Map;

public class BuscaCliente extends Fragment {

    EditText idBuscarCliente;
    Spinner cmbTipo;
    Button btnBuscarCliente;
    //String tipo[] = {"Selecione", "Código", "Nome", "CPF"};
    ArrayAdapter<String>adapter;
    Cliente c=new Cliente();
    Typeface font;
    ListView LV_BuscaCli;
    SimpleAdapter ADAhere;
    TextView TV_Header,memID_tv,memName_tv,memEmail_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_busca_cliente, container, false);
        TV_Header=(TextView) rootView.findViewById(R.id.TV_Header);
        LV_BuscaCli=(ListView)rootView.findViewById(R.id.LV_BuscaCli);
        idBuscarCliente=rootView.findViewById(R.id.idBuscarCliente);
        cmbTipo=rootView.findViewById(R.id.cmbTipo);
        btnBuscarCliente=(Button)rootView.findViewById(R.id.btnBuscarCliente);

        //adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, R.array.tipo);
        //cmbTipo.setAdapter(adapter);

        Spinner spinner = rootView.findViewById(R.id.cmbTipo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.tipo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(getActivity());

        btnBuscarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=idBuscarCliente.getText().toString();
                String tipo = cmbTipo.getSelectedItem().toString();

                if(a.trim().equals("")){
                    Toast.makeText(getActivity(),"Favor preencha o campo de pesquisa.",Toast.LENGTH_LONG).show();
                }
                else if(tipo.trim().equals("Selecione")){
                    Toast.makeText(getActivity(),"Favor selecione o tipo de busca.",Toast.LENGTH_LONG).show();
                }
                else{
                    List<Map<String,String>> MyData = null;
                    PesquisarCliente mydata =new PesquisarCliente();
                    //MyData= mydata.BuscarClienteNome(a);
                    if(tipo.trim().equals("Código")){
                        MyData=mydata.BuscarCliente(a,1);
                    }
                    else if(tipo.trim().equals("Nome")){
                        MyData=mydata.BuscarCliente(a,2);
                    }
                    else if(tipo.trim().equals("CPF")){
                        MyData=mydata.BuscarCliente(a,3);
                    }
                    else{
                        Toast.makeText(getActivity(),"Dados inseridos inválidos",Toast.LENGTH_LONG).show();
                    }

                    String[] fromwhere = { "ID","NomeCli","CPF" };

                    int[] viewswhere = {R.id.lblIDCli , R.id.lblCPF,R.id.lblNomeCli};

                    ADAhere = new SimpleAdapter(getActivity(), MyData,R.layout.activity_list_cliente, fromwhere, viewswhere);

                    LV_BuscaCli.setAdapter(ADAhere);

                    LV_BuscaCli.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                memID_tv = (TextView) view.findViewById(R.id.lblIDCli);
                                memName_tv = (TextView) view.findViewById(R.id.lblCPF);
                                memEmail_tv = (TextView) view.findViewById(R.id.lblNomeCli);

                                String memberID_val = memID_tv.getText().toString();
                                String memberName_val = memName_tv.getText().toString();
                                String memberEmail_val = memEmail_tv.getText().toString();
                                //Prepara Intent para envio dos dados para actvity de mofidicação:
                                Intent modif = new Intent(getContext(), VacinaFuncionario.class);
                                modif.putExtra("memberID", memberID_val);
                                modif.putExtra("memberName", memberName_val);
                                modif.putExtra("memberEmail", memberEmail_val);
                                //Aciona activity de modificações:
                                Toast.makeText(getActivity(), memberID_val, Toast.LENGTH_SHORT).show();
                                //ListarVacinas list=new ListarVacinas();
                                //list.setId_dose(memberID_val);
                                //list.carregarTodasVacinas(memberID_val);
                                startActivity(modif);
                                int idcli=Integer.parseInt(memberID_val);
                                c.setId(idcli);
                            } catch (Exception e) {
                                Log.e("Principal", e.toString());
                            }
                        }
                    });
                }
            }
        });
        return rootView;
    }
}