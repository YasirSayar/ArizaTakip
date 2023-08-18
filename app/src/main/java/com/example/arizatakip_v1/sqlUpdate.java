package com.example.arizatakip_v1;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class sqlUpdate {
    Connection connect;
    String ConnectionResult="";
    public void VeriGuncelle(int idsi,String islem){
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            if(connect !=null){
                String query="Update ArizaBildirimTakip SET DURUM='"+islem+"' WHERE ID='"+String.valueOf(idsi)+"'";
                Statement st= connect.createStatement();
                int rowsAffected = st.executeUpdate(query);
                if (rowsAffected > 0) {
                    System.out.println("Veritabanı güncellendi.");
                } else {
                    System.out.println("Güncelleme başarısız.");
                }
            }
        }
        catch (Exception ex){
            Log.e("Error",ex.getMessage());
        }
    }
}