package com.example.james.suv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class BD extends _Default implements Runnable {

/*public class BD {
    private Connection conn;
    private String host="";
    private String db="SUV";
    private String user="James";
    private String password="James 2015";
    public BD(){
        super();
        //ConnectionURL = "jdbc:jtds:sqlserver://" + server +";databaseName="+ database + ";user=" + user+ ";password=" + password + ";";
    }*/
    private Connection conn;
    private String host = "192.168.0.105";
    private String db = "suv";
    private String user = "James";
    private String pass = "James 2015";
    private String url = "jdbc:jtds:sqlserver://%s:%d/%s";

    public BD() {
        super();
        this.url = String.format(this.url,this.host, this.db);

        this.conecta();
        this.disconecta();
    }

    @Override
    public void run() {
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            this.conn = DriverManager.getConnection(this.url,this.user,this.pass);
            //ConnectionURL = "jdbc:jtds:sqlserver://" + server +";databaseName="+ database + ";user=" + user+ ";password=" + password + ";";
            //connection = DriverManager.getConnection(ConnectionURL);
        }catch (Exception e){
            this._mensagem = e.getMessage();
            this._status = false;
        }
    }

    private void conecta(){
        Thread thread = new Thread(this);
        thread.start();
        try{
            thread.join();
        }catch (Exception e){
            this._mensagem = e.getMessage();
            this._status = false;
        }
    }

    private void disconecta(){
        if (this.conn!= null){
            try{
                this.conn.close();
            }catch (Exception e){

            }finally {
                this.conn = null;
            }
        }
    }

    public ResultSet select(String query){
        this.conecta();
        ResultSet resultSet = null;
        try {
            resultSet = new ExecuteBD(this.conn, query).execute().get();
        }catch (Exception e){
            this._status = false;
            this._mensagem = e.getMessage();
        }
        return resultSet;
    }

    public ResultSet execute(String query){
        this.conecta();
        ResultSet resultSet = null;
        try {
            resultSet = new ExecuteBD(this.conn, query).execute().get();
        }catch (Exception e){
            this._status = false;
            this._mensagem = e.getMessage();
        }
        return resultSet;
    }

}
