package com.example.arizatakip_v1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class redTamamFragment extends Fragment {
    private ArizaAdapter adapter;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_red_tamam, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView RecyclerView = (RecyclerView) getView().findViewById(R.id.rv);
        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        sql_Veri tablo= new sql_Veri();

        Log.e("VERR",tablo.toString());


        adapter= new ArizaAdapter(requireContext(),tablo.VeriOlustur("TAMAMLANDI"));
        RecyclerView.setAdapter(adapter);
    }
}