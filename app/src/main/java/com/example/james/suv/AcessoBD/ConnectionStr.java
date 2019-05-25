package com.example.james.suv.AcessoBD;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionStr {

    @SuppressLint("NewApi")
    public Connection connectionclasss() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        String user = "James";
        String password = "James 2015";
        String database = "SUV";
        String server = "192.168.0.105";
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //ConnectionURL = "jdbc:jtds:sqlserver://" + server + ";databaseName=" + database + ";user=" + user + ";password=" + password + ";";
            //ConnectionURL="jdbc:sqlserver://james-server.database.windows.net;database=SUV;user=James@james-server;password=James@123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            //ConnectionURL = "jdbc:jtds:sqlserver://james-server.database.windows.net;databaseName=SUV;user=James@james-server;password=Senha@123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
			ConnectionURL = "jdbc:jtds:sqlserver://den1.mssql7.gear.host;databaseName=suv;user=suv;password=Senha@123;";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (SQLException se) {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}