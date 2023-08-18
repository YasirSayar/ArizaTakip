package com.example.arizatakip_v1;

import android.util.Log;

import androidx.annotation.Nullable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class sql_Veri {
    Connection connect;
    String ConnectionResult="";
    private ArrayList<Arizalar> arizalarArrayList;
    public List<Arizalar> VeriOlustur(String a){
        arizalarArrayList = new ArrayList<>();

        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            Log.e("TRY İÇİ","111111111");
            if(connect !=null){
                String query;
                if(a.equals("TAMAMLANDI")||a.equals("REDDEDİLDİ")){
                 query="Select * from ArizaBildirimTakip WHERE DURUM ='"+a +"'";}
                else  {
                     query="SELECT * FROM ArizaBildirimTakip\n" +
                             "WHERE DURUM = 'ISLEME ALINDI' OR DURUM IS NULL OR DURUM = ''\n" +
                             "ORDER BY\n" +
                             "    CASE\n" +
                             "        WHEN DURUM = 'ISLEME ALINDI' THEN 1\n" +
                             "        WHEN DURUM IS NULL OR DURUM = '' THEN 2\n" +
                             "    END,\n" +
                             "    DURUM;\n" ;//"+a+"'
                }
            //    else query="Select * from ArizaBildirimTakip";
               // String query="Select * from ArizaBildirimTakip";
                Statement st= connect.createStatement();
                ResultSet rs=st.executeQuery(query);



                while (rs.next()){
                    Arizalar a1=new Arizalar(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(1));
                    Log.e("Nesne",rs.getString(1));
                    arizalarArrayList.add(a1);
                   // a1=null ;
                }
            }
        }
        catch (Exception ex){
            Log.e("Error sqlveri Catch",ex.getMessage());
        }
        return arizalarArrayList;
    }
}
