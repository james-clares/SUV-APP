package com.example.james.suv.AcessoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListarVacinas {
    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;

    Cliente c = new Cliente();

    public List<Map<String, String>> doInBackground(int id_cli) {

        List<Map<String, String>> data = null;
        data = new ArrayList<Map<String, String>>();
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();        // Connect to database
            String query = "select a.*,b.nome_vacina from vacinas as a inner join Tipos_vacinas as b on a.id_tipo_vacina_fk=b.id_Tipo_Vacina where a.id_cli_fk=" + id_cli + "";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Map<String, String> datanum = new HashMap<String, String>();
                datanum.put("ID", rs.getString("id_tipo_vacina_fk"));
                datanum.put("IDCliente", rs.getString("id_cli_fk"));
                datanum.put("BCG_ID", rs.getString("nome_vacina"));
                data.add(datanum);
            }
            ConnectionResult = " successful";
            isSuccess = true;
            connect.close();
        } catch (Exception ex) {
            isSuccess = false;
            ConnectionResult = ex.getMessage();
        }
        return data;
    }

	public List<Map<String, String>> VacinasRecentesCliente(int id_cli) {

        List<Map<String, String>> data = null;
        data = new ArrayList<Map<String, String>>();
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();        // Connect to database
            String query = "VacinasRecentesCli " + id_cli + "";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Map<String, String> datanum = new HashMap<String, String>();
                datanum.put("ID", rs.getString("id_tipo_vacina_fk"));
                datanum.put("IDCliente", rs.getString("id_cli_fk")+" Data: "+rs.getString("data"));
                datanum.put("BCG_ID", rs.getString("nome_vacina"));
                data.add(datanum);
            }
            ConnectionResult = " successful";
            isSuccess = true;
            connect.close();
        } catch (Exception ex) {
            isSuccess = false;
            ConnectionResult = ex.getMessage();
        }
        return data;
    }
	
	public List<Map<String, String>> VacinasRecentesFunc(int id_func) {

        List<Map<String, String>> data = null;
        data = new ArrayList<Map<String, String>>();
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();        // Connect to database
            String query = "VacinasRecentesFunc " + id_func + "";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Map<String, String> datanum = new HashMap<String, String>();
                datanum.put("ID", rs.getString("id_tipo_vacina_fk"));
                datanum.put("IDCliente", rs.getString("id_func_fk")+" Data: "+rs.getString("data"));
                datanum.put("BCG_ID", rs.getString("nome_vacina"));
                rs.getDate("data");

                data.add(datanum);
            }
            ConnectionResult = " successful";
            isSuccess = true;
            connect.close();
        } catch (Exception ex) {
            isSuccess = false;
            ConnectionResult = ex.getMessage();
        }
        return data;
    }

    public boolean CadastrarVacina(String idVacina,String idCli,String dose,String data,String local,String RegProf) {
        boolean a=false;
        Funcionario func=new Funcionario();
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
			String query="exec insert1dose '"+idVacina+"','"+idCli+"','"+func.getId()+"','"+data+"','"+local+"','"+RegProf+"';";
            Statement stmt = connect.createStatement();
            stmt.execute(query);
            connect.close();
            a = true;
        } catch (Exception ex) {
            a = false;
        }
        return a;
    }

    public boolean AtualizarDose(String idVacina,String idCli,String dose,String data,String local,String RegProf) {
        boolean a=false;
        Funcionario func=new Funcionario();
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query="";
            if(dose.trim().equals("segunda_dose")){
                query="exec insert2dose '"+idVacina+"','"+idCli+"','"+func.getId()+"','"+data+"','"+local+"','"+RegProf+"';";
            }
            else if(dose.trim().equals("terceira_dose")){
                query="exec insert3dose '"+idVacina+"','"+idCli+"','"+func.getId()+"','"+data+"','"+local+"','"+RegProf+"';";
            }
			else if(dose.trim().equals("primeiro_reforco")){
                query="exec insert1reforco '"+idVacina+"','"+idCli+"','"+func.getId()+"','"+data+"','"+local+"','"+RegProf+"';";
            }
			else if(dose.trim().equals("segundo_reforco")){
                query="exec insert2reforco '"+idVacina+"','"+idCli+"','"+func.getId()+"','"+data+"','"+local+"','"+RegProf+"';";
            }
            Statement stmt = connect.createStatement();
            stmt.execute(query);
            connect.close();
            a = true;
        } catch (Exception ex) {
            a = false;
        }
        return a;
    }

    public String carregarIdCliente(String nome) {
        String idcliente="";
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "select id_cli from cliente where nome='"+nome+"'";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                idcliente=rs.getString("id_cli");
                connect.close();
            } else {
            }
        } catch (Exception ex) {
        }
        return idcliente;
    }

    public String carregarIdVacina(String nomevacina) {
        String idvacina="";
        Funcionario func=new Funcionario();
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "select id_Tipo_Vacina from Tipos_vacinas where nome_vacina='"+nomevacina+"' and empresa_id_fk="+func.getId_emp()+"";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                idvacina=rs.getString("id_Tipo_Vacina");
                connect.close();
            } else {
            }
        } catch (Exception ex) {
        }
        return idvacina;
    }
    /*Todas as vacinas*/
    public static int id_tipo_vacina;
    public static String nome_vacina;
    public static String id_primeira_dose;
    public static String data1;
    public static String id_segunda_dose;
    public static String data2;
    public static String id_terceira_dose;
    public static String data3;
	
	public static String id_primeiro_reforco;
    public static String data4;
	public static String id_segundo_reforco;
    public static String data5;

    /*Dose da vacina*/
    public static String id_dose;
    public static int id_Tipo_Vacina_fk;
    public static int id_cli_fk;
    public static int id_func_fk;
    public static String data;
    public static String local;
    public static String registro_prof;
    public static String nome_func;
    //public static String nome_vacina;

    public int getId_tipo_vacina() {
        return id_tipo_vacina;
    }

    public void setId_tipo_vacina(int id_tipo_vacina) {
        this.id_tipo_vacina = id_tipo_vacina;
    }

    public String getNome_vacina() {
        return nome_vacina;
    }

    public void setNome_vacina(String nome_vacina) {
        this.nome_vacina = nome_vacina;
    }

    public String getId_primeira_dose() {
        return id_primeira_dose;
    }

    public void setId_primeira_dose(String id_primeira_dose) {
        this.id_primeira_dose = id_primeira_dose;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getId_segunda_dose() {
        return id_segunda_dose;
    }

    public void setId_segunda_dose(String id_segunda_dose) {
        this.id_segunda_dose = id_segunda_dose;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getId_terceira_dose() {
        return id_terceira_dose;
    }

    public void setId_terceira_dose(String id_terceira_dose) {
        this.id_terceira_dose = id_terceira_dose;
    }

    public String getData3() {
        return data3;
    }

    public void setData3(String data3) {
        this.data3 = data3;
    }
	
	
	public String getId_primeiro_reforco() {
        return id_primeiro_reforco;
    }

    public void setId_primeiro_reforco(String id_primeiro_reforco) {
        this.id_primeiro_reforco = id_primeiro_reforco;
    }

    public String getData4() {
        return data4;
    }

    public void setData4(String data4) {
        this.data4 = data4;
    }	
	
	public String getId_segundo_reforco() {
        return id_segundo_reforco;
    }

    public void setId_segundo_reforco(String id_segundo_reforco) {
        this.id_segundo_reforco = id_segundo_reforco;
    }

    public String getData5() {
        return data5;
    }

    public void setData5(String data5) {
        this.data5 = data5;
    }


    public String getId_dose() {
        return id_dose;
    }

    public void setId_dose(String id_dose) {
        this.id_dose = id_dose;
    }

    public int getId_Tipo_Vacina_fk() {
        return id_Tipo_Vacina_fk;
    }

    public void setId_Tipo_Vacina_fk(int id_Tipo_Vacina_fk) {
        this.id_Tipo_Vacina_fk = id_Tipo_Vacina_fk;
    }

    public int getId_cli_fk() {
        return id_cli_fk;
    }

    public void setId_cli_fk(int id_cli_fk) {
        this.id_cli_fk = id_cli_fk;
    }

    public int getId_func_fk() {
        return id_func_fk;
    }

    public void setId_func_fk(int id_func_fk) {
        this.id_func_fk = id_func_fk;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getRegistro_prof() {
        return registro_prof;
    }

    public void setRegistro_prof(String registro_prof) {
        this.registro_prof = registro_prof;
    }

    public String getNome_func() {
        return nome_func;
    }

    public void setNome_func(String nome_func) {
        this.nome_func = nome_func;
    }

    public void carregarTodasVacinas(String tipovacina) {
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            Cliente c=new Cliente();
			String query="exec listarVacinas "+c.getId()+","+tipovacina+";";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                setId_tipo_vacina(rs.getInt("id_tipo_vacina_fk"));
                setNome_vacina(rs.getString("nome_vacina"));
                setData1(rs.getString("data1"));
                setData2(rs.getString("data2"));
                setData3(rs.getString("data3"));
				setData4(rs.getString("data4"));
				setData5(rs.getString("data5"));
                connect.close();
            } else {
            }
        } catch (Exception ex) {
        }
    }

    public void carregarDetalhesVacina(String prm) {
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            int tipo=0;
			if(prm.toString().equals("primeira")){
				tipo=1;
			}
			else if(prm.toString().equals("segunda")){
				tipo=2;
			}
			else if(prm.toString().equals("terceira")){
				tipo=3;
			}
			else if(prm.toString().equals("1reforco")){
				tipo=4;
			}
			else if(prm.toString().equals("2reforco")){
				tipo=5;
			}
            Cliente c=new Cliente();
			String query="exec listarDetalheVacina '"+tipo+"','"+getId_dose()+"','"+c.getId()+"';";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                setNome_vacina(rs.getString("nome_vacina"));
                setData(rs.getString("data"));
                setLocal(rs.getString("local"));
                setRegistro_prof(rs.getString("registro_prof"));
                setNome_func(rs.getString("nome_func"));
                connect.close();
            } else {
            }

        } catch (Exception ex) {
        }
    }
}