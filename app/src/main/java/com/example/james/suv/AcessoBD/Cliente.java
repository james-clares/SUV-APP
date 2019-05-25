package com.example.james.suv.AcessoBD;

import com.example.james.suv.BD;
import com.example.james.suv._Default;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Cliente extends _Default {
    private static int id;
    private static String nome;
    private static String sobrenome;
    private static String email;
    private static String sexo;
    private static String datanasc;
    private static String nacionalidade;
    private static String rg;
    private static String cpf;
    private static String endereco;
    private static String cep;
    private static String telefone;
    private static String telefone_cel;
    private static String senha;

    public Cliente(){
        super();
    }

    public ArrayList<Cliente> getLista(){
        BD db = new BD();
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM cliente where id_cli="+getId());
            if (resultSet != null){
                while (resultSet.next()){
                    Cliente obj = new Cliente();
                    obj.setNome(resultSet.getString("nome"));
                    obj.setSobreNome(resultSet.getString("sobre_nome"));
                    obj.setEmail(resultSet.getString("email"));
                    obj.setSexo(resultSet.getString("sexo"));
                    obj.setDataNasc(resultSet.getString("data_nasc"));
                    obj.setNacionalidade(resultSet.getString("nacionalidade"));
                    obj.setRG(resultSet.getString("rg"));
                    obj.setCPF(resultSet.getString("cpf"));
                    obj.setEndereco(resultSet.getString("endereco"));
                    obj.setCEP(resultSet.getString("cep"));
                    obj.setTelefone(resultSet.getString("telefone"));
                    obj.setTelefone_cel(resultSet.getString("telefone_cel"));
                    obj.setSenha(resultSet.getString("senha"));
                    lista.add(obj);
                    obj = null;
                }
            }
        }catch (Exception ex){
            this._mensagem = ex.getMessage();
            this._status = false;
        }
        return lista;
    }

    public void salvar(){
        String comando = "";
        if (this.getId() == -1){
            comando = String.format("INSERT INTO cliente (nome, sobre_nome, senha) VALUES ('%s','%s','%s');",
    //insert into cliente (nome,sobre_nome,email,sexo,data_nasc,nacionalidade,rg,cpf,endereco,cep,telefone,telefone_cel,senha)
                    this.getNome(),this.getSobreNome(),this.getSenha());
        }else{
            comando = String.format("UPDATE cliente SET nome = '%s', email = '%s', telefone = '%s' WHERE id_cli = %d;",
                    this.getNome(),this.getEmail(),this.getTelefone(),this.getId());
        }
        BD db = new BD();
        db.execute(comando);
        this._mensagem = db._mensagem;
        this._status = db._status;
    }

    Connection connect;

    public boolean Login(String nome,String senha) {
        boolean a = false;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "select * from cliente where nome= '" + nome + "' and senha = '" + senha + "'  ";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                setId(rs.getInt("id_cli"));
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

    public boolean CadastrarCliente() {
        boolean a;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "insert into cliente (nome,sobre_nome,email,sexo,data_nasc,nacionalidade,rg,cpf,endereco," +
                    "cep,telefone,telefone_cel,senha)" +
                    "values( '" + nome + "','" + sobrenome + "','" + email + "','" + sexo + "',convert(datetime,'" + datanasc + "',103)," +
                    "'" + nacionalidade + "','" + rg + "','" + cpf + "','" + endereco + "','" + cep + "','" + telefone + "','" + telefone_cel + "','" + senha + "'  )";
            Statement stmt = connect.createStatement();
            stmt.execute(query);
            connect.close();
            a = true;
        } catch (Exception ex) {
            a = false;
        }
        return a;
    }

    public boolean AlterarCliente() {
        boolean a = false;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();
            String query = "update cliente set nome='" + nome + "',sobre_nome='" + sobrenome + "',email='" + email + "',sexo='" + sexo + "',data_nasc=convert(datetime,'" + datanasc + "',103)," +
                    "nacionalidade='" + nacionalidade + "',rg='" + rg + "',cpf='" + cpf + "',endereco='" + endereco + "',cep='" + cep + "',telefone='" + telefone + "'," +
                    "telefone_cel='" + telefone_cel + "' where id_cli=" + getId() + "";
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
            String query = "update cliente set senha='" + novasenha + "' where id_cli=" + getId() + "";
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
            String query = "select * from cliente where cpf = '" + cpf + "'  ";
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
            String query = "update cliente set senha='" + senha + "' where cpf='" + cpf + "'";
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
            String query = "select nome,sobre_nome,email,sexo,convert(varchar,data_nasc,103)as data_nasc,nacionalidade,rg,cpf,endereco,cep,telefone,telefone_cel from cliente where id_cli=" + getId() + "";

            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                setNome(rs.getString("nome"));
                setSobreNome(rs.getString("sobre_nome"));
                setEmail(rs.getString("email"));
                setSexo(rs.getString("sexo"));
                setDataNasc(rs.getString("data_nasc"));
                setNacionalidade(rs.getString("nacionalidade"));
                setRG(rs.getString("rg"));
                setCPF(rs.getString("cpf"));
                setEndereco(rs.getString("endereco"));
                setCEP(rs.getString("cep"));
                setTelefone(rs.getString("telefone"));
                setTelefone_cel(rs.getString("telefone_cel"));
                setSenha(rs.getString("senha"));
                connect.close();
            } else {
            }
        } catch (Exception ex) {
        }
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobrenome;
    }

    public void setSobreNome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNasc() {
        return datanasc;
    }

    public void setDataNasc(String datanasc) {
        this.datanasc = datanasc;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
    public String getRG() {
        return rg;
    }

    public void setRG(String rg) {
        this.rg = rg;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getCEP() {
        return cep;
    }

    public void setCEP(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone_cel() {
        return telefone_cel;
    }

    public void setTelefone_cel(String telefone_cel) {
        this.telefone_cel = telefone_cel;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }}