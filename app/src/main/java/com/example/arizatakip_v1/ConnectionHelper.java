package com.example.arizatakip_v1;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection con;
    String uname,pass,ip,port,database;
    @SuppressLint("NewApi")
    public Connection connectionclass(){
        ip="*******";
        database="ArizaTakip";
        uname="sa";
        pass="***********";
        port = "";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;

        String ConnectionURL= null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ";"+ "databasename="+ database+";user="+uname+";password="+pass+";";
            connection= DriverManager.getConnection(ConnectionURL);
        }
        catch (Exception ex)
        {
            Log.e("Error Conn Catch",ex.getMessage());
        }
        return connection;
    }
}
