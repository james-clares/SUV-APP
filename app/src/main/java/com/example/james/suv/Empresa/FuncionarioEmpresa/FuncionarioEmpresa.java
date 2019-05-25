package com.example.james.suv.Empresa.FuncionarioEmpresa;

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

public class FuncionarioEmpresa extends Fragment {

    TextView TV_Header;
    Typeface font;
    ListView LV_Func;
    SimpleAdapter ADAhere;
    TextView memID_tv,memName_tv,memEmail_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_funcionario_empresa, container, false);

        TV_Header=(TextView) rootView.findViewById(R.id.TV_Header);
        LV_Func=(ListView)rootView.findViewById(R.id.LV_Func);

        List<Map<String,String>> MyData = null;
        ListarFuncionarios mydata =new ListarFuncionarios();
        MyData= mydata.doInBackground();
        String[] fromwhere = { "ID","NomeFunc","CPF" };

        int[] viewswhere = {R.id.lblIDFunc , R.id.lblNomeFunc,R.id.lblCPF};
        ADAhere = new SimpleAdapter(getActivity(), MyData,R.layout.activity_list_funcionario, fromwhere, viewswhere);
        LV_Func.setAdapter(ADAhere);

        LV_Func.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    memID_tv = (TextView) view.findViewById(R.id.lblIDFunc);
                    memName_tv = (TextView) view.findViewById(R.id.lblNomeFunc);//nome
                    memEmail_tv = (TextView) view.findViewById(R.id.lblCPF);//senha

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