package com.example.james.suv.AcessoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PesquisarCliente {
    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;

    public List<Map<String,String>> BuscarCliente(String info,int tipo) {

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
                String query="";
                if(tipo==1){
                    query = "select * from cliente where id_cli='"+info+"'";
                }
                else if(tipo==2){
                    query = "select * from cliente where nome like '"+info+"'%";
					//select*from cliente where nome like 'c%'
                }
                else if(tipo==3){
                    query = "select * from cliente where cpf like '"+info+"'%";
                }
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    Map<String,String> datanum=new HashMap<String,String>();
                    datanum.put("ID",rs.getString("id_cli"));
                    datanum.put("NomeCli",rs.getString("nome"));
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
