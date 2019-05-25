package com.example.james.suv.Empresa.ClienteEmpresa;

import com.example.james.suv.AcessoBD.ConnectionStr;
import com.example.james.suv.AcessoBD.Empresa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListarClientes {
    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;
	Empresa emp=new Empresa();
	
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
                //String query = "select a.id_cli_fk,b.*,c.nome from vacinas as a inner join Tipos_vacinas as b on a.id_tipo_vacina_fk=b.id_Tipo_Vacina inner join cliente as c on a.id_cli_fk=c.id_cli where b.empresa_id_fk="+emp.getId()+"";
                String query="select*from cliente;";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    Map<String,String> datanum=new HashMap<String,String>();
                    //datanum.put("ID",rs.getString("id_cli_fk"));
                    datanum.put("ID",rs.getString("id_cli"));
                    //datanum.put("Nome",rs.getString("nome_vacina"));
                    datanum.put("Nome",rs.getString("nome"));

                    datanum.put("Senha",rs.getString("senha"));
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