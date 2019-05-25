package com.example.james.suv.AcessoBD;

import com.example.james.suv.BD;
import com.example.james.suv._Default;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Funcionario extends _Default {
    private static int id;
    private static int id_emp;
    private static String nome;
    private static String tipo;
    private static String genero;
    private static String idade;
    private static String cpf;
    private static String senha;

    private static String nome_empresa;

    private static String tiposVacinas[];

    public Funcionario(){
        super();
    }

    Connection connect;

    public boolean Login(String nome,String senha) {
        boolean a = false;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "select * from funcionario where nome= '" + nome + "' and senha = '" + senha + "'  ";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                setId(rs.getInt("id_func"));
                CarregarDados();
                connect.close();
                a = true;
            } else {
                a = false;
            }
        } catch (Exception ex) {
            a = false;
        }
        return a;
    }

    public boolean CadastrarFuncionario() {
        boolean a = false;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "insert into funcionario (id_empresa_fk,nome,tipo,genero,idade,cpf,senha)" +
                    "values( '" + id_emp + "','" + nome + "','" + tipo + "','" + genero + "','" + idade + "'," +
                    "'" + cpf + "','" + senha + "'  )";
            Statement stmt = connect.createStatement();
            stmt.execute(query);
            connect.close();
            a = true;
        } catch (Exception ex) {
            a = false;
        }
        return a;
    }

    public boolean AlterarFuncionario() {
        boolean a = false;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "update funcionario set nome='" + nome + "',tipo='" + tipo + "',genero='" + genero + "'," +
                    "idade='" + idade + "',cpf='" + cpf + "' where id_func=" + getId() + "";
            Statement stmt = connect.createStatement();
            stmt.execute(query);
            connect.close();
            a = true;
        } catch (Exception ex) {
            a = false;
        }
        return a;
    }

    public boolean AlterarSenha(String novasenha) {
        boolean a = false;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "update funcionario set senha='" + novasenha + "' where id_func=" + getId() + "";
            Statement stmt = connect.createStatement();
            stmt.execute(query);
            connect.close();
            a = true;

        } catch (Exception ex) {
            a = false;
        }
        return a;
    }

    public boolean VerificaCPF(String cpf) {
        boolean a = false;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "select * from funcionario where cpf = '" + cpf + "'  ";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                connect.close();
                a = true;
            } else {
                a = false;
            }

        } catch (Exception ex) {
            a = false;
        }
        return a;
    }

    public boolean RecuperarSenha(String cpf,String senha) {
        boolean a = false;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "update funcionario set senha='" + senha + "' where cpf='" + cpf + "'";
            Statement stmt = connect.createStatement();
            stmt.execute(query);
            connect.close();
            a = true;
        } catch (Exception ex) {
            a = false;
        }
        return a;
    }


    public void CarregarDados() {
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "select a.*,b.nome as nome_empresa from funcionario as a inner join empresa as b on a.id_empresa_fk=b.id_emp where a.id_func=" + getId() + "";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                setId_emp(rs.getInt("id_empresa_fk"));
                setNome(rs.getString("nome"));
                setTipo(rs.getString("tipo"));
                setGenero(rs.getString("genero"));
                setIdade(rs.getString("idade"));
                setCPF(rs.getString("cpf"));
                setSenha(rs.getString("senha"));
                setNome_empresa(rs.getString("nome_empresa"));
                connect.close();
            } else {

            }
        } catch (Exception ex) {

        }
    }

    public int CarregarListaVacinas() {
        int cont = 0;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "select * from Tipos_vacinas where empresa_id_fk="+getId_emp();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> ListVacinas = new ArrayList<String>();

            while (rs.next()) {
                ListVacinas.add(rs.getString("nome_vacina"));
                cont++;
            }
        } catch (Exception ex) {
        }
        return cont;
    }

    public ArrayList <String> CarregarVacinas() {
        ArrayList<String> listVacinas = new ArrayList<String>();

        int limite = 0;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "select nome_vacina from Tipos_vacinas where empresa_id_fk="+getId_emp();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            listVacinas.add("Selecione");
            while (rs.next()) {
                listVacinas.add("" + rs.getString("nome_vacina") + "");
                limite++;
            }
        } catch (Exception ex) {
            //vacinas[0]=""+limite+"";
        }
        return listVacinas;
    }

    public ArrayList<String>listId=new ArrayList<String>();

    public ArrayList <String> CarregarClientes() {
        ArrayList<String> listClientes = new ArrayList<String>();

        int limite = 0;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "select* from cliente";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            listClientes.add("Selecione");
            while (rs.next()) {
                listId.add(""+rs.getString("id_cli")+"");
                listClientes.add("" + rs.getString("nome") + "");
                limite++;
            }
        } catch (Exception ex) {
            //vacinas[0]=""+limite+"";
        }
        return listClientes;
    }

    public void apagar(){
        String comando = String.format("DELETE FROM cliente WHERE id_cli = %d;",this.getId());
        BD db = new BD();
        db.execute(comando);
        this._mensagem = db._mensagem;
        this._status = db._status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome_empresa() {
        return nome_empresa;
    }

    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }

    public String[] getTiposVacinas() {
        return tiposVacinas;
    }

    public void setTiposVacinas(String tiposVacinas[]) {
        this.tiposVacinas = tiposVacinas;
    }
}