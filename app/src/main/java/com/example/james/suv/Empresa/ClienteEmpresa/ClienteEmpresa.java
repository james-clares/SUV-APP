package com.example.james.suv.Empresa.ClienteEmpresa;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.suv.ModificarContato;
import com.example.james.suv.R;

import java.util.List;
import java.util.Map;


public class ClienteEmpresa extends Fragment {

    TextView TV_Header;
    Typeface font;
    ListView LV_Country;
    SimpleAdapter ADAhere;
    TextView memID_tv,memName_tv,memEmail_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_cliente_empresa, container, false);

        TV_Header=(TextView) rootView.findViewById(R.id.TV_Header);
        LV_Country=(ListView)rootView.findViewById(R.id.LV_Country);

        List<Map<String,String>> MyData = null;
        ListarClientes mydata =new ListarClientes();
        MyData= mydata.doInBackground();
        String[] fromwhere = { "ID","Nome","Senha" };

        int[] viewswhere = {R.id.lblID , R.id.lblNome,R.id.lblSenha};
        ADAhere = new SimpleAdapter(getActivity(), MyData,R.layout.activity_listtemplate, fromwhere, viewswhere);
        LV_Country.setAdapter(ADAhere);

        LV_Country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    memID_tv = (TextView) view.findViewById(R.id.lblID);
                    memName_tv = (TextView) view.findViewById(R.id.lblNome);//nome
                    memEmail_tv = (TextView) view.findViewById(R.id.lblSenha);//senha

                    String memberID_val=memID_tv.getText().toString();
                    String memberName_val=memName_tv.getText().toString();
                    String memberEmail_val=memEmail_tv.getText().toString();
                    //Prepara Intent para envio dos dados para actvity de mofidicação:
                    Intent modif = new Intent(getActivity(), ModificarContato.class);
                    modif.putExtra("memberID", memberID_val);
                    modif.putExtra("memberName", memberName_val);
                    modif.putExtra("memberEmail", memberEmail_val);
                    //Aciona activity de modificações:
                    Toast.makeText(getActivity(), memberID_val, Toast.LENGTH_SHORT).show();
                    startActivity(modif);
                }
                catch(Exception e){
                    Log.e("Principal",e.toString());
                }
            }
        });
        return rootView;
    }
}