package com.example.james.suv.Empresa.FuncionarioEmpresa;

import com.example.james.suv.AcessoBD.ConnectionStr;
import com.example.james.suv.AcessoBD.Empresa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListarFuncionarios {
    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;
	Empresa emp = new Empresa();
	
    public List<Map<String,String>> doInBackground() {

        List<Map<String, String>> data = null;
        data = new ArrayList<Map<String, String>>();
        try
        {
            ConnectionStr conStr=new ConnectionStr();
            connect =conStr.connectionclasss();        // Connect to database
            if (connect == null)
            {
                ConnectionResult = "Verifique o acesso a internet!";
            }
            else
            {
                // Change below query according to your own database.
                String query = "select * from funcionario where id_empresa_fk="+emp.getId()+"";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    Map<String,String> datanum=new HashMap<String,String>();
                    datanum.put("ID",rs.getString("id_func"));
                    datanum.put("NomeFunc",rs.getString("nome"));
                    datanum.put("CPF",rs.getString("cpf"));
                    data.add(datanum);
                }
                ConnectionResult = " successful";
                isSuccess=true;
                connect.close();
            }
        }
        catch (Exception ex)
        {
            isSuccess = false;
            ConnectionResult = ex.getMessage();
        }
        return data;
    }
}