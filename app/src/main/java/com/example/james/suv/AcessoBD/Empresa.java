package com.example.james.suv.AcessoBD;

import com.example.james.suv.BD;
import com.example.james.suv._Default;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Empresa extends _Default {
    private static int id;
    private static String Nome;
    private static String Raz_social;
    private static String CNPJ;
    private static String Endereco;
    private static String CEP;
    private static String Estado;
    private static String Telefone;
    private static String Usuario;
    private static String Email;
    private static String Senha;

    private static ArrayList nomes;
    private static int Idcliente;

    public Empresa(){
        super();
    }

    public ArrayList<Empresa> getLista(){
        BD db = new BD();
        ArrayList<Empresa> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM empresa");
            if (resultSet != null){
                while (resultSet.next()){
                    Empresa obj = new Empresa();
                    obj.setId(resultSet.getInt("id_emp"));
                    obj.setNome(resultSet.getString("nome"));
                    obj.setUsuario(resultSet.getString("usuario"));
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
                    this.getNome(),this.getUsuario(),this.getSenha());
        }else{
            comando = String.format("UPDATE cliente SET nome = '%s', email = '%s', telefone = '%s' WHERE id_cli = %d;",
                    this.getNome(),this.getCNPJ(),this.getTelefone(),this.getId());
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
            String query = "select * from empresa where usuario= '" + nome + "' and senha = '" + senha + "'  ";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                setId(rs.getInt("id_emp"));
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

    public boolean CadastrarEmpresa() {
        boolean a = false;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "insert into empresa (nome,raz_social,CNPJ,endereco,cep,estado,telefone,usuario,email,senha)values" +
                    "( '" + Nome + "','" + Raz_social + "','" + CNPJ + "','" + Endereco + "','" + CEP + "','" + Estado + "','" + Telefone + "'," +
                    "'" + Usuario + "','" + Email + "','" + Senha + "'  )";
            Statement stmt = connect.createStatement();
            stmt.execute(query);
            connect.close();
            a = true;
        } catch (Exception ex) {
            a = false;
        }
        return a;
    }

    public boolean AlterarEmpresa() {
        boolean a = false;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "update empresa set nome='" + Nome + "',raz_social='" + Raz_social + "',CNPJ='" + CNPJ + "',endereco='" + Endereco + "',cep='" + CEP + "'," +
                    "estado='" + Estado + "',telefone='" + Telefone + "',usuario='" + Usuario + "',email='" + Email + "',senha='" + Senha + "' where id_emp=" + getId() + "";
            Statement stmt = connect.createStatement();
            stmt.execute(query);
            connect.close();
            a = true;
        } catch (Exception ex) {
            a = false;
        }
        return a;
    }

    public boolean AlterarSenha(String senha) {
        boolean a = false;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "update empresa set senha='" + senha + "' where id_emp=" + getId() + "";
            Statement stmt = connect.createStatement();
            stmt.execute(query);
            connect.close();
            a = true;
        } catch (Exception ex) {
            a = false;
        }
        return a;
    }

    public boolean deletarCliente(long idcliente) {
        boolean a = false;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "delete from cliente where id_cli=" + idcliente + "";
            Statement stmt = connect.createStatement();
            stmt.execute(query);
            connect.close();
            a = true;
        } catch (Exception ex) {
            a = false;
        }
        return a;
    }

    public void CarregarDados(){
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "select * from empresa where id_emp=" + getId() + "";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                setNome(rs.getString("nome"));
                setRaz_social(rs.getString("raz_social"));
                setCNPJ(rs.getString("cnpj"));
                setEndereco(rs.getString("endereco"));
                setCEP(rs.getString("cep"));
                setEstado(rs.getString("estado"));
                setTelefone(rs.getString("telefone"));
                setUsuario(rs.getString("usuario"));
                setEmail(rs.getString("email"));
                setSenha(rs.getString("senha"));
                connect.close();
            } else {
            }
        } catch (Exception ex) {
        };
    }

    public boolean CadastrarVacina(String nomevacina) {
        boolean a = false;
        try {
            ConnectionStr conStr = new ConnectionStr();
            connect = conStr.connectionclasss();            // Connect to database
            String query = "insert into Tipos_vacinas (empresa_id_fk,nome_vacina)values('" + id + "','" + nomevacina + "')";
            Statement stmt = connect.createStatement();
            stmt.execute(query);
            connect.close();
            a = true;
        } catch (Exception ex) {
            a = false;
        }
        return a;
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
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public String getRaz_social() {
        return Raz_social;
    }

    public void setRaz_social(String raz_social) {
        this.Raz_social = raz_social;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String cnpj) {
        this.CNPJ = cnpj;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        this.Endereco = endereco;
    }
    public String getCEP() {
        return CEP;
    }

    public void setCEP(String cep) {
        this.CEP = cep;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        this.Estado = estado;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        this.Telefone = telefone;
    }
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        this.Usuario = usuario;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        this.Senha = senha;
    }

    public int getIdcliente() {
        return Idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.Idcliente = idcliente;
    }

    public ArrayList getNomes() {
        return nomes;
    }

    public void setNomes(ArrayList nomes) {
        this.nomes = nomes;
    }
}